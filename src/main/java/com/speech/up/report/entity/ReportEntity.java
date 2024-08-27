package com.speech.up.report.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.record.entity.RecordEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "report")
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReportEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "report_id")
	private Long reportId;

	@OneToOne
	@JoinColumn(name = "record_id")
	@JsonBackReference
	private RecordEntity recordId;

	@Column(updatable = false)
	private double score;

	@Column(updatable = false)
	private String recognized;

	@Column(updatable = false)
	private LocalDateTime createdAt;

	private boolean isPublic;

	private boolean isUse;

	private ReportEntity(RecordEntity recordEntity, String recognized, double score) {
		this.recordId = recordEntity;
		this.recognized = recognized;
		this.score = score;
		this.createdAt = LocalDateTime.now();
		this.isPublic = true;
		this.isUse = true;
	}

	public static ReportEntity create(RecordEntity recordEntity, String recognized, double score) {
		return new ReportEntity(recordEntity, recognized, score);
	}
}
