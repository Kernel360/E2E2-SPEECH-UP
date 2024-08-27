package com.speech.up.report.service;

import org.springframework.stereotype.Service;

import com.speech.up.record.repository.RecordRepository;
import com.speech.up.report.entity.ReportEntity;
import com.speech.up.report.repository.ReportRepository;
import com.speech.up.record.entity.RecordEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {
	private final ReportRepository reportRepository;
	private final RecordRepository recordRepository;

	public void saveReport(RecordEntity recordEntity, String recognized, double score) {
		ReportEntity reportEntity = ReportEntity.create(recordEntity, recognized, score);

		reportRepository.save(reportEntity);
	}

	public ReportEntity getReportFromRecordId(Long recordId) {
		return reportRepository.findReportEntityByReportId(recordId);
	}

	public String getScriptFromRecordId (Long recordId) {
		RecordEntity recordEntity = recordRepository.findById(recordId).get();
		return recordEntity.getScript().getContent();
	}
}
