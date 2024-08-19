package com.speech.up.record.repository;

import com.speech.up.record.entity.RecordEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<RecordEntity, Long> {
    List<RecordEntity> findByScriptScriptIdAndIsUseTrue(Long scriptId);
}
