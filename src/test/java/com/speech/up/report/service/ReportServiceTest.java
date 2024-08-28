package com.speech.up.report.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.speech.up.record.entity.RecordEntity;
import com.speech.up.record.repository.RecordRepository;
import com.speech.up.report.entity.ReportEntity;
import com.speech.up.report.repository.ReportRepository;
import com.speech.up.script.entity.ScriptEntity;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
	@Mock
	ReportRepository reportRepository;
	@Mock
	RecordRepository recordRepository;

	@InjectMocks
	private ReportService reportService;
	@Mock
	RecordEntity recordEntity;

	String recognized;
	double score;
	Long recordId;
	LocalDateTime createdAt;

	@BeforeEach
	void setUp() {
		recognized = "recognized";
		score = 1.0;
		recordId = 2L;
	}

	@DisplayName("리포트 저장하기")
	@Test
	public void saveReportTest() {
		// Given
		ReportEntity expectedReportEntity = ReportEntity.create(recordEntity, recognized, score);

		// When
		reportService.saveReport(recordEntity, recognized, score);

		// Then
		assertNotNull(expectedReportEntity);
	}

	@DisplayName("리포트 레코드아이디로 불러오기")
	@Test
	public void getReportByRecordIdTest() {
		// Given
		ReportEntity expectedReportEntity = ReportEntity.create(recordEntity, recognized, score);
		when(reportRepository.findReportEntityByRecordIdRecordId(recordId))
			.thenReturn(Optional.of(expectedReportEntity));

		// When
		ReportEntity actualEntity = reportService.getReportFromRecordId(recordId);

		// Then
		assertNotNull(actualEntity);
		assertEquals(expectedReportEntity, actualEntity);
		verify(reportRepository, times(1)).findReportEntityByRecordIdRecordId(recordId);
	}

	@DisplayName("스크립트 레코드아이디로 불러오기")
	@Test
	public void getReportByScriptIdTest() {
		// Given
		String expectedScriptContent = "expected script content";

		ScriptEntity scriptEntity = mock(ScriptEntity.class);
		when(scriptEntity.getContent()).thenReturn(expectedScriptContent);

		RecordEntity recordEntity = mock(RecordEntity.class);
		when(recordEntity.getScript()).thenReturn(scriptEntity);

		when(recordRepository.findById(recordId)).thenReturn(Optional.of(recordEntity));

		// When
		String actualScriptContent = reportService.getScriptFromRecordId(recordId);

		// Then
		assertNotNull(actualScriptContent);
		assertEquals(expectedScriptContent, actualScriptContent);
		verify(recordRepository, times(1)).findById(recordId);
	}

}
