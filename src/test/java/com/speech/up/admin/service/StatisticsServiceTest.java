package com.speech.up.admin.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.speech.up.admin.dto.StatisticsGet;
import com.speech.up.admin.entity.StatisticsEntity;
import com.speech.up.admin.repository.StatisticsRepository;

public class StatisticsServiceTest {

	@Mock
	StatisticsEntity statisticsEntity;
	@Mock
	StatisticsRepository statisticsRepository;

	@InjectMocks
	StatisticsService statisticsService;

	Long id;
	Long report;
	Long script;
	Long record;
	double score;
	LocalDateTime createAt;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		id = 1L;
		report = 1L;
		script = 1L;
		record = 1L;
		createAt = LocalDateTime.now();
		score = 1.1;

		statisticsEntity = mock(StatisticsEntity.class);

		when(statisticsEntity.getId()).thenReturn(id);
		when(statisticsEntity.getReport()).thenReturn(report);
		when(statisticsEntity.getScript()).thenReturn(script);
		when(statisticsEntity.getRecord()).thenReturn(record);
		when(statisticsEntity.getScore()).thenReturn(score);
		when(statisticsEntity.getCreateAt()).thenReturn(createAt);

	}

	@DisplayName("통계 가져오기")
	@Test
	void getStatisticsTest() {
		//given
		when(statisticsRepository.findTopByOrderByCreateAtDesc()).thenReturn(statisticsEntity);

		//when
		StatisticsGet.Response expected = StatisticsGet.Response.toResponse(statisticsEntity);
		StatisticsGet.Response actualResponse = statisticsService.getStatistics();

		//then
		assertEquals(expected.getCreateAt(), actualResponse.getCreateAt());
		assertEquals(expected.getReport(), actualResponse.getReport());
		assertEquals(expected.getScript(), actualResponse.getScript());
		assertEquals(expected.getRecord(), actualResponse.getRecord());
		assertEquals(expected.getScore(), actualResponse.getScore());

		verify(statisticsRepository, times(1)).findTopByOrderByCreateAtDesc();
	}
}
