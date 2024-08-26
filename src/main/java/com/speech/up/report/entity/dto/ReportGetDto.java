package com.speech.up.report.entity.dto;

import lombok.Getter;
import lombok.ToString;

public class ReportGetDto {
	@Getter
	@ToString
	public static class Response{
		private final Long reportId;
		private final double score;
		private final boolean isUse;

		private Response(Long reportId, double score, boolean isUse) {
			this.reportId = reportId;
			this.score = score;
			this.isUse = isUse;
		}
	}
}
