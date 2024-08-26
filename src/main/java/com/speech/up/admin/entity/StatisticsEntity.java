package com.speech.up.admin.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.admin.dto.StatisticsAdd;
import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.service.dto.ScriptAddDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "statistics")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StatisticsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long report;

	private Long script;

	private Long record;

	private double score;

	private LocalDateTime createAt;

	public StatisticsEntity(StatisticsAdd.Request statisticsAdd) {
		this.id = statisticsAdd.getId();
		this.report = statisticsAdd.getReport();
		this.script = statisticsAdd.getScript();
		this.record = statisticsAdd.getRecord();
		this.score = statisticsAdd.getScore();
		this.createAt = statisticsAdd.getCreateAt();
	}

	public static StatisticsEntity create(StatisticsAdd.Request statisticsAdd) {
		return new StatisticsEntity(statisticsAdd);
	}

}
