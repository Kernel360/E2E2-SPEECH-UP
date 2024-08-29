package com.speech.up.api.etri.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.speech.up.api.etri.service.VoiceToTextService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ETRIApiController {
	private final VoiceToTextService voiceToTextService;

	@PostMapping(value = "/api/upload")
	public void createTask(
		@RequestPart("script") String script,
		@RequestPart("recordId") String recordId
	) {
		voiceToTextService.callRecognitionApi(script, Long.parseLong(recordId));
	}
}