package com.speech.up.record.controller;

import com.speech.up.record.entity.RecordEntity;
import com.speech.up.record.service.RecordService;
import com.speech.up.record.service.dto.RecordAddDto;
import com.speech.up.record.service.dto.RecordGetDto;
import com.speech.up.record.service.dto.RecordIsUseDto;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasAnyRole('GENERAL_USER', 'ADMIN_USER')")
	public ResponseEntity<List<RecordGetDto.Response>> getRecordALl(@PathVariable Long scriptId) {
		return ResponseEntity.ok(recordService.getRecordList(scriptId));
	}

	@PostMapping("")
	@PreAuthorize("hasAnyRole('GENERAL_USER', 'ADMIN_USER')")
	public ResponseEntity<RecordAddDto.Response> addRecord(
		@RequestPart("file") MultipartFile file,
		@RequestParam("languageCode") String languageCode,
		@RequestParam("scriptId") Long scriptId
	) throws IOException, UnsupportedAudioFileException {
		RecordAddDto.Response response = recordService.addRecord(file, languageCode, scriptId);
		return ResponseEntity.ok(response);
	}

	@PatchMapping("")
	@PreAuthorize("hasAnyRole('GENERAL_USER', 'ADMIN_USER')")
	public ResponseEntity<RecordIsUseDto.Response> deleteRecord(
		@RequestBody RecordIsUseDto.Request recordIsUseRequestDto
	) {
		return ResponseEntity.ok(recordService.deleteRecord(recordIsUseRequestDto));
	}

	@PatchMapping("/{recordId}/analyze")
	@PreAuthorize("hasAnyRole('GENERAL_USER', 'ADMIN_USER')")
	public ResponseEntity<Void> isRecordAnalyzed(
		@PathVariable Long recordId
	) {
		recordService.analyzed(recordId);
		return ResponseEntity.ok().build();
	}
}
