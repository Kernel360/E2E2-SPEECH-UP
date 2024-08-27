package com.speech.up.record.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.record.entity.RecordEntity;
import com.speech.up.script.entity.ScriptEntity;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public class RecordIsUseDto {
	@Getter
	@ToString
	@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Request {
		private final RecordEntity recordEntity;

		public Request(RecordEntity recordEntity) {
			this.recordEntity = recordEntity;
		}
	}

	@Getter
	@ToString
	@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Response {
		private final Long recordId;
		private final boolean isUse;

		public Response(RecordEntity recordEntity) {
			this.recordId = recordEntity.getRecordId();
			this.isUse = recordEntity.isUse();
		}
	}

	public static Response toResponse(RecordEntity recordEntity) {
		return new Response(recordEntity);
	}
}

