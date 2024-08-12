package com.speech.up.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.speech.up.report.entity.ReportEntity;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
}
