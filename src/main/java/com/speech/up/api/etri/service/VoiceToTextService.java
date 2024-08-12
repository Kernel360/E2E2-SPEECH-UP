package com.speech.up.api.etri.service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.speech.up.api.etri.dto.AiRequest;
import com.speech.up.api.etri.dto.RequestVoiceToTextApiDto;
import com.speech.up.api.etri.dto.RequestVoiceToTextApiDto.ArgumentDTO;
import com.speech.up.api.etri.dto.ResponseVoiceToTextApiDto;

@Service
public class VoiceToTextService {

	@Value(value = "${api.voice.accessKey}")
	private String accessKey;
	@Value(value = "${api.voice.url}")
	private String recognized;
	@Value(value = "${api.voice.score}")
	private String pronunciation;

	private final Gson gson = new Gson();

	// 음성인식
	public ResponseEntity<ResponseVoiceToTextApiDto> callRecognitionApi(AiRequest aiRequest) {
		try {
			// 녹음 파일
			Path path = Paths.get(aiRequest.getFilePath());
			byte[] audioBytes = Files.readAllBytes(path);
			String audioContents = Base64.getEncoder().encodeToString(audioBytes);
			// 요청 객체 생성
			RequestVoiceToTextApiDto requestDTO = RequestVoiceToTextApiDto.builder().request_id(
				aiRequest.getRequestId()).argument(
				ArgumentDTO.builder()
					.language_code(aiRequest.getLanguageCode())
					.audio(audioContents)
					.script(aiRequest.getScript()).build()).build();

			ResponseEntity<ResponseVoiceToTextApiDto> responseDTO = sendPostRequest(requestDTO, aiRequest.getApiType());
			if (responseDTO.getStatusCode() != HttpStatus.OK) {
				return new ResponseEntity<>(responseDTO.getBody(), HttpStatus.BAD_REQUEST);
			}

			return responseDTO;

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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