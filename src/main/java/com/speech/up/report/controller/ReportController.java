package com.speech.up.report.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.speech.up.report.service.ReportService;
import com.speech.up.report.service.dto.ReportAddDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {
	private final ReportService reportService;

	@PostMapping("/{recordId}")
	public ReportAddDto.Response analyticVoiceToText(
		@RequestParam String filePath, @PathVariable Long recordId, @RequestBody ReportAddDto.Request request) {
		return (reportService.getReport(filePath,recordId,request));
	}

}
