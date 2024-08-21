package com.speech.up.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.speech.up.report.entity.ReportEntity;
import com.speech.up.report.service.ReportService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReportPageController {
	private final ReportService reportService;

	@GetMapping("/reports/{recordId}")
	public String getReport(
		@PathVariable Long recordId,
		Model model
	){
		ReportEntity reportEntity = reportService.getReportFromRecordId(recordId);
		String script = reportService.getScriptFromRecordId(recordId);
		model.addAttribute("script", script);
		model.addAttribute("recognized", reportEntity.getRecognized());
		model.addAttribute("score", reportEntity.getScore());
		return "report";
	}
}
