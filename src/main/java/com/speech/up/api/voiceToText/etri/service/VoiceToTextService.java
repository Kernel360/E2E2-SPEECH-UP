package com.speech.up.api.voiceToText.etri.service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
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
import com.speech.up.api.voiceToText.etri.dto.RequestVoiceToTextApiDTO;
import com.speech.up.api.voiceToText.etri.dto.RequestVoiceToTextApiDTO.ArgumentDTO;
import com.speech.up.api.voiceToText.etri.dto.ResponseVoiceToTextApiDTO;

@Service
public class VoiceToTextService {
	@Value(value = "${api.voice.url}")
	private String aiApiUrl;
	@Value(value = "${api.voice.accessKey}")
	private String accessKey;
	@Value(value = "${api.voice.language-code}")
	private String languageCode;

	private final Gson gson = new Gson();

	// api 호출 결과를 리턴하는 메서드
	public ResponseEntity<ResponseVoiceToTextApiDTO> callRecognitionApi(String audioFilePath) {
		try {
			// 녹음 파일
			Path path = Paths.get(audioFilePath);
			byte[] audioBytes = Files.readAllBytes(path);
			String audioContents = Base64.getEncoder().encodeToString(audioBytes);
			// 요청 객체 생성
			ArgumentDTO argumentDTO = new ArgumentDTO();
			argumentDTO.setLanguage_code(languageCode);
			argumentDTO.setAudio(audioContents);
			RequestVoiceToTextApiDTO requestDTO = new RequestVoiceToTextApiDTO();
			requestDTO.setRequest_id("reserved field");
			requestDTO.setArgument(argumentDTO);

			ResponseEntity<ResponseVoiceToTextApiDTO> responseDTO = sendPostRequest(requestDTO);
			if (responseDTO.getStatusCode() != HttpStatus.OK) {
				return new ResponseEntity<>(responseDTO.getBody(), HttpStatus.BAD_REQUEST);
			}

			return responseDTO;

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	ResponseEntity<ResponseVoiceToTextApiDTO> sendPostRequest(RequestVoiceToTextApiDTO requestDTO) throws IOException {
		URL url = new URL(aiApiUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		con.setRequestProperty("Authorization", accessKey);

		try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
			wr.write(gson.toJson(requestDTO).getBytes(StandardCharsets.UTF_8));
			wr.flush();
		}

		// int responseCode = con.getResponseCode();
		if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
			return new ResponseEntity<>(HttpStatus.valueOf(con.getResponseCode()));
		}

		try (InputStream is = con.getInputStream()) {
			String responseBody = new String(is.readAllBytes());
			System.out.println(responseBody);
			// Deserialize response into ResponseDTO
			ResponseVoiceToTextApiDTO responseDTO = gson.fromJson(responseBody, ResponseVoiceToTextApiDTO.class);

			if (responseDTO.getResult() != 0) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		}
	}
}
