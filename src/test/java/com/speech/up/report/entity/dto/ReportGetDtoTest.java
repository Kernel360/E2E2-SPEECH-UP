package com.speech.up.report.entity.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReportGetDtoTest {

	@DisplayName("ReportGetDto.Response 생성자 및 getter 테스트")
	@Test
	void testResponseDto() {
		// Given
		Long reportId = 1L;
		double score = 95.5;
		boolean isUse = true;

		// When
		ReportGetDto.Response responseDto = ReportGetDto.Response.toResponse(reportId, score, isUse);

		// Then
		assertEquals(reportId, responseDto.getReportId(), "Report ID should match");
		assertEquals(score, responseDto.getScore(), "Score should match");
		assertEquals(isUse, responseDto.isUse(), "isUse flag should match");
	}
}
