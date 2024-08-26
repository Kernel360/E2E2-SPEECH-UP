package com.speech.up.admin.dto;

import java.time.LocalDateTime;

import com.speech.up.admin.entity.StatisticsEntity;

import lombok.Getter;
import lombok.ToString;

public class StatisticsGet {

	@Getter
	@ToString
	public static class Response {
		private final Long report;
		private final Long script;
		private final Long record;
		private final double score;
		private final LocalDateTime createAt;

		private Response(StatisticsEntity statisticsEntity) {
			this.report = statisticsEntity.getReport();
			this.script = statisticsEntity.getScript();
			this.record = statisticsEntity.getRecord();
			this.score = statisticsEntity.getScore();
			this.createAt = statisticsEntity.getCreateAt();
		}
		public static Response toResponse(StatisticsEntity statisticsEntity) {
			return new StatisticsGet.Response(statisticsEntity);
		}

	}
}
