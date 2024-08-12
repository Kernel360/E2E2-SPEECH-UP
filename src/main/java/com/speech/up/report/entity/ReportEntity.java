package com.speech.up.report.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.report.entity.type.ReportContentAndScore;
import com.speech.up.report.service.dto.ReportAddDto;
import com.speech.up.script.entity.RecordEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "report")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReportEntity extends BaseReportEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reportId;

	@OneToOne
	@JoinColumn(name = "record_id")
	@JsonBackReference
	private RecordEntity record;

	private Double score;

	private String content;

	private boolean isPublic;

	private boolean isUse;

	private ReportEntity(ReportContentAndScore reportContentAndScore, ReportAddDto.Request request) {
		this.score = reportContentAndScore.getScore();
		this.content = reportContentAndScore.getContent();
		this.isUse = true;
		this.isPublic = true;
		this.record = request.getRecordEntity();
	}

	public static ReportEntity create(ReportContentAndScore reportContentAndScore, ReportAddDto.Request request){
			return new ReportEntity(reportContentAndScore, request);
	}

}
