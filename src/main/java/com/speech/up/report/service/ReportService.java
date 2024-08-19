package com.speech.up.report.service;

import org.springframework.stereotype.Service;

import com.speech.up.report.entity.ReportEntity;
import com.speech.up.report.repository.ReportRepository;
import com.speech.up.record.entity.RecordEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {
	private final ReportRepository reportRepository;

	public void saveReport(RecordEntity recordEntity, String recognized, double score) {
		ReportEntity reportEntity = ReportEntity.create(recordEntity, recognized, score);

		reportRepository.save(reportEntity);
	}
}
