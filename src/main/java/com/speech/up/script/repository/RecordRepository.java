package com.speech.up.script.repository;

import com.speech.up.script.entity.RecordEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<RecordEntity, Long> {
    List<RecordEntity> findByScriptScriptIdAndIsUseTrue(Long userUserId);
}
