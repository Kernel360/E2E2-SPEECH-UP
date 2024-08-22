package com.speech.up.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.speech.up.report.entity.ReportEntity;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
	ReportEntity findReportEntityByReportId(Long recordId);
}
