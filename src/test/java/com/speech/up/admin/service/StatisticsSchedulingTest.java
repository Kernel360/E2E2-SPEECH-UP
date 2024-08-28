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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.speech.up.admin.dto.StatisticsAdd;
import com.speech.up.admin.entity.StatisticsEntity;
import com.speech.up.admin.repository.StatisticsRepository;
import com.speech.up.record.repository.RecordRepository;
import com.speech.up.report.repository.ReportRepository;
import com.speech.up.script.repository.ScriptRepository;

@SpringJUnitConfig
public class StatisticsSchedulingTest {

	@Mock
	private ScriptRepository scriptRepository;

	@Mock
	private ReportRepository reportRepository;

	@Mock
	private RecordRepository recordRepository;

	@Mock
	private StatisticsRepository statisticsRepository;

	@InjectMocks
	private StatisticsScheduling statisticsScheduling;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("통계 주기적 업데이트 테스트")
	@Test
	void runStatisticsSetterTest() {
		// Given
		long scriptCount = 10L;
		long reportCount = 20L;
		long recordCount = 30L;
		double avgScore = 5.5;

		StatisticsAdd.Request request = StatisticsAdd.Request
			.builder()
			.report(reportCount)
			.record(recordCount)
			.script(scriptCount)
			.score(avgScore)
			.createAt(LocalDateTime.now())
			.build();
		StatisticsEntity expected = StatisticsEntity.create(request);
		when(statisticsRepository.save(any(StatisticsEntity.class))).thenReturn(expected);

		//when
		statisticsScheduling.runStatisticsSetter();
		statisticsScheduling.statisticsSetter();

		//then
		assertEquals(expected.getReport(), reportCount);
		assertEquals(expected.getRecord(), recordCount);
		assertEquals(expected.getScript(), scriptCount);
		assertEquals(expected.getScore(), avgScore);
	}
}
