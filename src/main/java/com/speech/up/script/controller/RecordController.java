package com.speech.up.script.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.service.RecordService;
import com.speech.up.script.service.dto.RecordAddDto;
import com.speech.up.script.service.dto.RecordGetDto;
import com.speech.up.script.service.dto.RecordIsUseDto;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/speech-record")
@RequiredArgsConstructor
public class 	RecordController {
	private final RecordService recordService;
	private final ObjectMapper objectMapper;

	@GetMapping("/{scriptId}")
	public ResponseEntity<List<RecordGetDto.Response>> getRecordALl(@PathVariable Long scriptId) {
		return ResponseEntity.ok(recordService.getRecordList(scriptId));
	}

	@PostMapping("")
	public ResponseEntity<RecordAddDto.Response> addRecord(
		@RequestPart("file") MultipartFile file,
		@RequestParam("audioPath") String audioPath,
		@RequestParam("languageCode") String languageCode,
		@RequestParam("script") String scriptJson
	) throws IOException {
		ScriptEntity script = objectMapper.readValue(scriptJson, ScriptEntity.class);
		RecordAddDto.Request recordAddRequestDto = new RecordAddDto.Request(file, audioPath, languageCode, script);
		return ResponseEntity.ok(recordService.addRecord(recordAddRequestDto));
	}

	@PatchMapping("")
	public ResponseEntity<RecordIsUseDto.Response> deleteRecord(
		@RequestBody RecordIsUseDto.Request recordIsUseRequestDto
	) {
		return ResponseEntity.ok(recordService.deleteRecord(recordIsUseRequestDto));
	}
}
