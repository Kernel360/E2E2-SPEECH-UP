package com.speech.up.api.etri.service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.speech.up.api.converter.WavToRaw;
import com.speech.up.api.etri.dto.AiRequest;
import com.speech.up.api.etri.dto.RequestVoiceToTextApiDto;
import com.speech.up.api.etri.dto.ResponseVoiceToTextApiDto;
import com.speech.up.api.etri.type.ApiType;
import com.speech.up.api.etri.url.UrlCollector;

@Service
public class VoiceToTextService {

	private static final Logger log = LoggerFactory.getLogger(VoiceToTextService.class);
	@Value(value = "${api.voice.accessKey}")
	private String accessKey;
	@Value(value = "${api.voice.url}")
	private String recognized;
	@Value(value = "${api.voice.score}")
	private String pronunciation;

	private final Gson gson = new Gson();

	// 음성인식
	public ResponseEntity<ResponseVoiceToTextApiDto> callRecognitionApi(String requestId, ApiType apiType, String languageCode, String script,
		MultipartFile file) {
		try {
			WavToRaw wavToRaw = new WavToRaw();
			byte[] audioBytes = wavToRaw.convertToRawPcm(file);
			String audioContents = Base64.getEncoder().encodeToString(audioBytes);

			RequestVoiceToTextApiDto requestDTO = RequestVoiceToTextApiDto.builder()
				.request_id(requestId).
				argument(RequestVoiceToTextApiDto.ArgumentDTO.builder()
					.language_code(languageCode)
					.audio(audioContents)
					.script(script).build()).build();

			ResponseEntity<ResponseVoiceToTextApiDto> responseDTO = sendPostRequest(requestDTO, apiType);
			if (responseDTO.getStatusCode() != HttpStatus.OK) {
				return new ResponseEntity<>(responseDTO.getBody(), HttpStatus.BAD_REQUEST);
			}

			return responseDTO;

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (UnsupportedAudioFileException e) {
			throw new RuntimeException(e);
		}
	}

	URL getUrl(ApiType apiType) throws MalformedURLException {
		UrlCollector urlCollector = new UrlCollector(recognized, pronunciation);
		return urlCollector.getApiUrl(apiType);
	}

	ResponseEntity<ResponseVoiceToTextApiDto> sendPostRequest(RequestVoiceToTextApiDto requestDTO,
		ApiType apiType) throws IOException {
		URL url = getUrl(apiType);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		con.setRequestProperty("Authorization", accessKey);

		try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
			wr.write(gson.toJson(requestDTO).getBytes(StandardCharsets.UTF_8));
			wr.flush();
		}

		if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
			return new ResponseEntity<>(HttpStatus.valueOf(con.getResponseCode()));
		}

		try (InputStream is = con.getInputStream()) {
			String responseBody = new String(is.readAllBytes());
			ResponseVoiceToTextApiDto responseDTO = gson.fromJson(responseBody, ResponseVoiceToTextApiDto.class);

			if (responseDTO.getResult() != 0) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		}
	}
}