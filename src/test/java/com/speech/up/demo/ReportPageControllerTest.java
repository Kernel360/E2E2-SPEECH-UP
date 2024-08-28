package com.speech.up.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.speech.up.report.entity.ReportEntity;
import com.speech.up.report.service.ReportService;

class ReportPageControllerTest {
	@InjectMocks
	ReportPageController reportPageController;

	@Mock
	ReportService reportService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("특정 레코드 ID에 대한 보고서를 조회하여, 이를 표시하는 페이지를 반환합니다.")
	@Test
	void getReportTest() {
		Long recordId = 1L;
		Model model = mock(Model.class);
		ReportEntity reportEntity = mock(ReportEntity.class);
		String script = "mock script";

		when(reportService.getReportFromRecordId(recordId)).thenReturn(reportEntity);
		when(reportService.getScriptFromRecordId(recordId)).thenReturn(script);

		String actual = reportPageController.getReport(recordId, model);

		assertNotNull(actual);
		assertEquals("report", actual);
	}
}