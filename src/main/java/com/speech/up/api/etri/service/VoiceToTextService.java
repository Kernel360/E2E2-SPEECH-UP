package com.speech.up.api.etri.service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.speech.up.api.etri.dto.RequestPronunciationDto;
import com.speech.up.api.etri.dto.RequestRecognizedDto;
import com.speech.up.api.etri.dto.ResponsePronunciationApiDto;
import com.speech.up.api.etri.dto.ResponseRecognizedDto;
import com.speech.up.api.etri.type.ApiType;
import com.speech.up.api.etri.url.UrlCollector;
import com.speech.up.common.exception.http.BadRequestException;
import com.speech.up.report.service.ReportService;
import com.speech.up.record.entity.RecordEntity;
import com.speech.up.record.repository.RecordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VoiceToTextService {

	private static final Logger log = LoggerFactory.getLogger(VoiceToTextService.class);

	@Value("${api.voice.accessKey}")
	private String accessKey;

	@Value("${api.voice.url}")
	private String recognizedUrl;

	@Value("${api.voice.score}")
	private String pronunciationUrl;

	private final Gson gson = new Gson();
	private final ReportService reportService;
	private final RecordRepository recordRepository;

	public void callRecognitionApi(String script, Long recordId) {
		RecordEntity recordEntity = getRecordEntity(recordId);

		try {
			String audioContents = encodeAudioToBase64(recordEntity.getAudio());

			RequestPronunciationDto pronunciationRequest = createPronunciationRequest(audioContents, script);
			RequestRecognizedDto recognizedRequest = createRecognizedRequest(audioContents, recordEntity.getLanguageCode());

			ResponseEntity<ResponseRecognizedDto> recognizedResponse = sendPostRequest(ApiType.RECOGNIZED, recognizedRequest, ResponseRecognizedDto.class);
			ResponseEntity<ResponsePronunciationApiDto> pronunciationResponse = sendPostRequest(ApiType.PRONUNCIATION, pronunciationRequest, ResponsePronunciationApiDto.class);

			handleApiResponses(recognizedResponse, pronunciationResponse);

			ResponseRecognizedDto recognizedBody = recognizedResponse.getBody();
			ResponsePronunciationApiDto pronunciationBody = pronunciationResponse.getBody();

			saveReportIfValid(recognizedBody, pronunciationBody, recordEntity);
		} catch (IOException e) {
			throw new RuntimeException("Error during API request", e);
		}
	}

	private RecordEntity getRecordEntity(Long recordId) {
		return recordRepository.findById(recordId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid record ID: " + recordId));
	}

	private String encodeAudioToBase64(byte[] audio) {
		return Base64.getEncoder().encodeToString(audio);
	}

	private RequestPronunciationDto createPronunciationRequest(String audioContents, String script) {
		return RequestPronunciationDto.createPro("reserved field", audioContents, script);
	}

	private RequestRecognizedDto createRecognizedRequest(String audioContents, String languageCode) {
		return RequestRecognizedDto.createRec("reserved field", audioContents, languageCode);
	}

	private <T> ResponseEntity<T> sendPostRequest(ApiType apiType, Object requestDTO, Class<T> responseType) throws IOException {
		URL url = getUrl(apiType);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		configureConnection(con);

		try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
			String jsonRequest = gson.toJson(requestDTO);
			log.info("Sending request to {} API: {}", apiType, jsonRequest);
			wr.write(jsonRequest.getBytes(StandardCharsets.UTF_8));
			wr.flush();
		}

		int responseCode = con.getResponseCode();
		log.info("Response code from {} API: {}", apiType, responseCode);

		if (responseCode != HttpURLConnection.HTTP_OK) {
			String errorResponse = readErrorResponse(con);
			log.error("Error response from {} API: {}", apiType, errorResponse);
			return new ResponseEntity<>(HttpStatus.valueOf(responseCode));
		}

		try (InputStream is = con.getInputStream()) {
			String responseBody = new String(is.readAllBytes(), StandardCharsets.UTF_8);
			log.info("Response body from {} API: {}", apiType, responseBody);
			T responseDTO = gson.fromJson(responseBody, responseType);
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		}
	}

	private URL getUrl(ApiType apiType) throws MalformedURLException {
		UrlCollector urlCollector = new UrlCollector(recognizedUrl, pronunciationUrl);
		return urlCollector.getApiUrl(apiType);
	}

	private void configureConnection(HttpURLConnection con) throws ProtocolException {
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		con.setRequestProperty("Authorization", accessKey);
	}

	private String readErrorResponse(HttpURLConnection con) throws IOException {
		try (InputStream errorStream = con.getErrorStream()) {
			if (errorStream != null) {
				return new String(errorStream.readAllBytes(), StandardCharsets.UTF_8);
			} else {
				return "No error stream available";
			}
		}
	}

	private void handleApiResponses(ResponseEntity<ResponseRecognizedDto> recognizedResponse,
		ResponseEntity<ResponsePronunciationApiDto> pronunciationResponse) {
		if (recognizedResponse.getStatusCode() != HttpStatus.OK) {
			throw new BadRequestException("Recognized API response: " + recognizedResponse.getStatusCode());
		}

		if (pronunciationResponse.getStatusCode() != HttpStatus.OK) {
			throw new BadRequestException("Pronunciation API response: " + pronunciationResponse.getStatusCode());
		}
	}

	private void saveReportIfValid(ResponseRecognizedDto recognizedBody,
		ResponsePronunciationApiDto pronunciationBody,
		RecordEntity recordEntity) {
		if (recognizedBody != null && pronunciationBody != null) {
			String recognized = recognizedBody.getReturn_object().getRecognized();
			Double score = pronunciationBody.getReturn_object().getScore();
			reportService.saveReport(recordEntity, recognized, score);
		}
	}
}