package com.speech.up.report.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.speech.up.record.repository.RecordRepository;
import com.speech.up.report.entity.ReportEntity;
import com.speech.up.report.repository.ReportRepository;
import com.speech.up.record.entity.RecordEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {
	private static final Logger log = LoggerFactory.getLogger(ReportService.class);
	private final ReportRepository reportRepository;

	public void saveReport(RecordEntity recordEntity, String recognized, double score) {
		ReportEntity reportEntity = ReportEntity.create(recordEntity, recognized, score);

		reportRepository.save(reportEntity);
	}

	public ReportEntity getReport(Long recordId) {
		return reportRepository.findByRecordId(recordId);
	}
}
