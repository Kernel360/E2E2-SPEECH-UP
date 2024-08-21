package com.speech.up.report.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.speech.up.record.entity.RecordEntity;
import com.speech.up.report.entity.ReportEntity;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
	@Query(value = "SELECT * FROM report WHERE `record_id` = :recordId", nativeQuery = true)
	ReportEntity findByRecordId(Long recordId);
}
