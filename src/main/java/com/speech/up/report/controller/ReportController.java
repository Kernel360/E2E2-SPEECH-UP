package com.speech.up.report.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.speech.up.report.entity.ReportEntity;
import com.speech.up.report.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class ReportController {
	private final ReportService reportService;

	@GetMapping("/{reportId}")
	public ResponseEntity<ReportEntity> getReport(
		@PathVariable String reportId
	){
		return ResponseEntity.ok(reportService.getReport(Long.parseLong(reportId)));
	}
}
