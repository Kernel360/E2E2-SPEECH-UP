package com.speech.up.script.repository;

import com.speech.up.script.entity.ScriptEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScriptRepository extends JpaRepository<ScriptEntity, Long> {
	List<ScriptEntity> findByUserUserIdAndIsUseTrue(Long userUserId);
	long countByUserUserIdAndIsUseTrue(Long userUserId);
}
