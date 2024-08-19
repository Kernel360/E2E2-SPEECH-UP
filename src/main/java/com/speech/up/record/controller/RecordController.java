package com.speech.up.record.controller;

import com.speech.up.record.service.RecordService;
import com.speech.up.record.service.dto.RecordAddDto;
import com.speech.up.record.service.dto.RecordGetDto;
import com.speech.up.record.service.dto.RecordIsUseDto;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import javax.sound.sampled.UnsupportedAudioFileException;

@RestController
@RequestMapping("/speech-record")
@RequiredArgsConstructor
public class RecordController {
	private final RecordService recordService;

	@GetMapping("/{scriptId}")
	public ResponseEntity<List<RecordGetDto.Response>> getRecordALl(@PathVariable Long scriptId) {
		return ResponseEntity.ok(recordService.getRecordList(scriptId));
	}

	@PostMapping("")
	public ResponseEntity<RecordAddDto.Response> addRecord(
		@RequestPart("file") MultipartFile file,
		@RequestParam("languageCode") String languageCode,
		@RequestParam("scriptId") Long scriptId // scriptId 추가
	) throws IOException, UnsupportedAudioFileException {
		RecordAddDto.Response response = recordService.addRecord(file, languageCode, scriptId);
		return ResponseEntity.ok(response);
	}

	@PatchMapping("")
	public ResponseEntity<RecordIsUseDto.Response> deleteRecord(
		@RequestBody RecordIsUseDto.Request recordIsUseRequestDto
	) {
		return ResponseEntity.ok(recordService.deleteRecord(recordIsUseRequestDto));
	}
}
