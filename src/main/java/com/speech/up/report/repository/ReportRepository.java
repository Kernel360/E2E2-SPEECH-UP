package com.speech.up.report.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.speech.up.report.entity.ReportEntity;
import com.speech.up.report.entity.dto.ReportGetDto;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
	Optional<ReportEntity> findReportEntityByReportId(Long recordId);
	List<ReportGetDto.Response> findAllBy();
}
