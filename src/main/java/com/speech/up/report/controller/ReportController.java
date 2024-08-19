package com.speech.up.report.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.speech.up.report.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class ReportController {
	private final ReportService reportService;

	/*public ResponseEntity<ResponseVoiceToTextApiDto> getReport(
		@RequestBody Long reportId
	){
		return reportService.getReport();
	}*/
}
