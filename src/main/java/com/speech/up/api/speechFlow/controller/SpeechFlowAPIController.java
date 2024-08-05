package com.speech.up.api.speechFlow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.speech.up.api.speechFlow.service.VoiceToTextSpeechFlowService;

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
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create task");
			}
			return ResponseEntity.ok(taskId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
		}
	}

}
