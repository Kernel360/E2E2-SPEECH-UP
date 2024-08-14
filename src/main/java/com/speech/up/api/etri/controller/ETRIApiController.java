package com.speech.up.api.etri.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.speech.up.api.etri.dto.AiRequest;
import com.speech.up.api.etri.dto.ResponseVoiceToTextApiDto;
import com.speech.up.api.etri.service.VoiceToTextService;
import com.speech.up.api.etri.type.ApiType;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ETRIApiController {
	private final VoiceToTextService voiceToTextService;

	@PostMapping(value = "/api/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseVoiceToTextApiDto> createTask(
		@RequestPart("requestId") String requestId,
		@RequestParam("apiType") ApiType apiType,
		@RequestPart("languageCode") String languageCode,
		@RequestPart("script") String script,
		@RequestPart("myFile") MultipartFile multipartFile) {

		return voiceToTextService.callRecognitionApi(requestId, apiType, languageCode, script, multipartFile);
	}
}