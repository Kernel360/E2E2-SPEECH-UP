package com.speech.up.admin.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class StatisticsAdd {
	@Getter
	@Builder
	@ToString
	public static class Request{
		private final Long id;
		private final Long report;
		private final Long script;
		private final Long record;
		private final double score;
		private final LocalDateTime createAt;

		private Request(Long id, Long report, Long script, Long record, double score, LocalDateTime createAt) {
			this.id = id;
			this.report = report;
			this.script = script;
			this.record = record;
			this.score = score;
			this.createAt = createAt;
		}
	}
}
