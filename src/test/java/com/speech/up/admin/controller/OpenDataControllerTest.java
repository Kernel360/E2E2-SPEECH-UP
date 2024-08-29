package com.speech.up.admin.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.speech.up.admin.service.StatisticsService;

public class OpenDataControllerTest {

	@Mock
	StatisticsService statisticsService;

	@InjectMocks
	OpenDataController openDataController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("통계 테이블의 가장 최근 데이터를 반환")
	@Test
	void getStatisticsTest() {

		openDataController.getStatistics();

		verify(statisticsService, times(1)).getStatistics();
	}
}
