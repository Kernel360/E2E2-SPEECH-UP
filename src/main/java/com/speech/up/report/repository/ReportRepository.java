package com.speech.up.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.speech.up.report.entity.ReportEntity;
import com.speech.up.report.entity.dto.ReportGetDto;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
	ReportEntity findReportEntityByReportId(Long recordId);
	List<ReportGetDto.Response> findAllBy();

}
