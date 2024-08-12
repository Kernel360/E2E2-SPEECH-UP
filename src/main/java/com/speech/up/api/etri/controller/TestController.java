package com.speech.up.api.etri.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.speech.up.api.etri.dto.AiRequest;
import com.speech.up.api.etri.dto.ResponseVoiceToTextApiDto;
import com.speech.up.api.etri.service.VoiceToTextService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {
	private final VoiceToTextService voiceToTextService;

	@PostMapping("/api")
	public ResponseEntity<ResponseVoiceToTextApiDto> createTask(
		@RequestBody AiRequest aiRequest) {

		return voiceToTextService.callRecognitionApi(aiRequest);
	}

}