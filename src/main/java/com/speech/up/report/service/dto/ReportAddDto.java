package com.speech.up.report.service.dto;

import java.time.LocalDateTime;

import com.speech.up.report.entity.ReportEntity;
import com.speech.up.script.entity.RecordEntity;

import lombok.Getter;
import lombok.ToString;

public class ReportAddDto {

	@Getter
	@ToString
	public static class Request {
		private final RecordEntity recordEntity;
		public Request(RecordEntity recordEntity) {
			this.recordEntity = recordEntity;
		}

	}

	@Getter
	@ToString
	public static class Response{
		private final Long recordId;
		private final String content;
		private final Double score;
		private final boolean isPublic;
		private final boolean isUse;
		private final LocalDateTime createdAt;


		public Response(ReportEntity reportEntity) {
			this.content = reportEntity.getContent();
			this.score = reportEntity.getScore();
			this.isPublic = reportEntity.isPublic();
			this.isUse = reportEntity.isUse();
			this.recordId =reportEntity.getReportId();
			this.createdAt = reportEntity.getCreatedAt();
		}
	}
	public static Response toResponse(ReportEntity reportEntity) {
		return new Response(reportEntity);
	}


}
