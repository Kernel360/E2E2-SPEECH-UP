package com.speech.up.report.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.speech.up.record.repository.RecordRepository;
import com.speech.up.report.entity.ReportEntity;
import com.speech.up.report.repository.ReportRepository;
import com.speech.up.record.entity.RecordEntity;
import com.speech.up.script.entity.ScriptEntity;

import jakarta.persistence.EntityNotFoundException;
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
		return reportRepository.findReportEntityByReportId(recordId)
			.orElseThrow(() -> new EntityNotFoundException("not found Report by RecordId : " + recordId));
	}

	public String getScriptFromRecordId(Long recordId) {
		return recordRepository.findById(recordId)
			.map(RecordEntity::getScript)
			.map(ScriptEntity::getContent)
			.orElseThrow(() -> new NoSuchElementException("not found Script by recordId : " + recordId));
	}
}
