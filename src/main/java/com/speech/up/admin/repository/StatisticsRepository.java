package com.speech.up.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.speech.up.admin.entity.StatisticsEntity;

public interface StatisticsRepository extends JpaRepository<StatisticsEntity, Long> {
	StatisticsEntity findTopByOrderByCreateAtDesc();
}
