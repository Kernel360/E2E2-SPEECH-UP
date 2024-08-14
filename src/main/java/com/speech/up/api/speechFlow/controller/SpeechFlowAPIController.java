package com.speech.up.api.speechFlow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.speech.up.api.speechFlow.service.VoiceToTextSpeechFlowService;
import com.speech.up.common.exception.http.InternalServerErrorException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/speech-flow")
public class SpeechFlowAPIController {

	private final VoiceToTextSpeechFlowService voiceToTextSpeechFlowService;

	@PostMapping("")
	public ResponseEntity<String> createTask(
		@RequestParam String filePath) {
		try {
			String taskId = voiceToTextSpeechFlowService.getResult(filePath);
			if (taskId == null) {
				throw new InternalServerErrorException("Task id is null");
			}
			return ResponseEntity.ok(taskId);
		} catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
		}
	}

}
