package com.speech.up.report.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.speech.up.report.entity.ReportEntity;
import com.speech.up.report.entity.dto.ReportGetDto;

public class ReportRepositoryTest {
	@Mock
	private ReportRepository reportRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("리포트아이디로 리포트 내용 찾기")
	@Test
	public void findReportByReportIdTest() {
		//given
		Optional<ReportEntity> reportEntity = Optional.ofNullable(mock(ReportEntity.class));
		Long id = 1L;

		//when
		when(reportRepository.findReportEntityByRecordIdRecordId(id)).thenReturn(reportEntity);

		//then
		assertEquals(reportRepository.findReportEntityByRecordIdRecordId(id), reportEntity);
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

}
