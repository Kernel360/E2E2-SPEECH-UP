package com.speech.up.report.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;

import com.speech.up.report.entity.ReportEntity;
import com.speech.up.report.entity.dto.ReportGetDto;

public class ReportRepositoryTest {
	@Mock
	private ReportRepository reportRepository;

	private MockitoSession mockitoSession;

	@BeforeEach
	void setUp() {
		mockitoSession = Mockito.mockitoSession()
			.initMocks(this)
			.startMocking();
	}

	@DisplayName("리포트아이디로 리포트 내용 찾기")
	@Test
	public void findReportByReportIdTest() {
		//given
		ReportEntity reportEntity = mock(ReportEntity.class);
		Long id = 1L;

		//when
		when(reportRepository.findReportEntityByReportId(id)).thenReturn(reportEntity);

		//then
		assertEquals(reportRepository.findReportEntityByReportId(id), reportEntity);
	}

	@DisplayName("모든 리포트 가져오기")
	@Test
	public void getAllReportsTest() {
		//given
		List<ReportGetDto.Response> reportGetDtoResponse = Collections.singletonList(mock(ReportGetDto.Response.class));

		//when
		when(reportRepository.findAllBy()).thenReturn(reportGetDtoResponse);

		//then
		assertEquals(reportRepository.findAllBy(), reportGetDtoResponse);
	}

	@AfterEach
	void tearDown() {
		mockitoSession.finishMocking();
	}
}
