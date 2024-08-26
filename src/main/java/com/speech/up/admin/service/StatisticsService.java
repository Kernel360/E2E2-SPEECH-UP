package com.speech.up.admin.service;

import org.springframework.stereotype.Service;

import com.speech.up.admin.dto.StatisticsGet;
import com.speech.up.admin.entity.StatisticsEntity;
import com.speech.up.admin.repository.StatisticsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatisticsService {

	private final StatisticsRepository statisticsRepository;

	public StatisticsGet.Response getStatistics() {
		StatisticsEntity result = statisticsRepository.findTopByOrderByCreateAtDesc();
		return StatisticsGet.Response.toResponse(result);
	}
}
