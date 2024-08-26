package com.speech.up.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.speech.up.report.entity.ReportEntity;
import com.speech.up.report.service.ReportService;

import lombok.RequiredArgsConstructor;

/**
 * ReportPageController는 보고서 페이지와 관련된 요청을 처리하는 컨트롤러입니다.
 * 이 컨트롤러는 특정 레코드 ID에 대한 보고서를 조회하여, 이를 보여주는 페이지를 반환합니다.
 */
@Controller
@RequiredArgsConstructor
public class ReportPageController {

	private final ReportService reportService;

	/**
	 * 특정 레코드 ID에 대한 보고서를 조회하여, 이를 표시하는 페이지를 반환합니다.
	 * 보고서 엔티티와 해당 스크립트를 모델에 추가하여, 뷰에서 사용할 수 있도록 합니다.
	 *
	 * @param recordId 보고서를 조회할 레코드의 ID
	 * @param model 뷰에 전달할 데이터 모델
	 * @return 보고서 페이지의 이름
	 */
	@GetMapping("/reports/{recordId}")
	public String getReport(
		@PathVariable Long recordId,
		Model model
	) {
		// 레코드 ID를 기반으로 보고서 엔티티를 조회
		ReportEntity reportEntity = reportService.getReportFromRecordId(recordId);

		// 레코드 ID를 기반으로 스크립트 정보를 조회
		String script = reportService.getScriptFromRecordId(recordId);

		// 모델에 보고서와 스크립트 정보를 추가
		model.addAttribute("script", script);
		model.addAttribute("recognized", reportEntity.getRecognized());
		model.addAttribute("score", reportEntity.getScore());

		// 보고서 페이지를 반환
		return "report";
	}
}
