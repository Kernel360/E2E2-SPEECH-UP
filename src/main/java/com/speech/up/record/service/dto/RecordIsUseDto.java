package com.speech.up.record.service.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.record.entity.RecordEntity;
import com.speech.up.report.entity.ReportEntity;
import com.speech.up.script.entity.ScriptEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public class RecordIsUseDto {
	@Getter
	@ToString
	public static class Request {
		private final Long recordId;
		private final byte[] audio;
		private final String languageCode;
		private final boolean isAnalyzed;
		private final ScriptEntity script;
		private final boolean isUse;
		private final ReportEntity report;

		public Request(RecordEntity recordEntity) {
			this.recordId = recordEntity.getRecordId();
			this.audio = recordEntity.getAudio();
			this.languageCode = recordEntity.getLanguageCode();
			this.isAnalyzed = recordEntity.isAnalyzed();
			this.script = recordEntity.getScript();
			this.isUse = recordEntity.isUse();
			this.report = recordEntity.getReport();
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

