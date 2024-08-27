package com.speech.up.report.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.speech.up.record.entity.RecordEntity;
import com.speech.up.report.entity.ReportEntity;
import com.speech.up.report.repository.ReportRepository;
import com.speech.up.script.entity.ScriptEntity;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
	@Mock
	private ReportRepository reportRepository;

	@InjectMocks
	private ReportService reportService;

	@DisplayName("리포트 저장하기")
	@Test
	public void saveReportTest() {

		// given
		RecordEntity recordEntity = mock(RecordEntity.class); // RecordEntity 모의
		String recognized = "Test Recognition";
		double score = 3.5;

		// when
		reportService.saveReport(recordEntity, recognized, score);

		// then
		ArgumentCaptor<ReportEntity> reportEntityCaptor = ArgumentCaptor.forClass(ReportEntity.class);
		verify(reportRepository).save(reportEntityCaptor.capture());

		ReportEntity capturedReportEntity = reportEntityCaptor.getValue();

		ReportEntity expectedReportEntity = ReportEntity.create(recordEntity, recognized, score);

		assertEquals(expectedReportEntity.getRecordId(), capturedReportEntity.getRecordId());
		assertEquals(expectedReportEntity.getScore(), capturedReportEntity.getScore());
		assertEquals(expectedReportEntity.getRecognized(), capturedReportEntity.getRecognized());
	}

	@DisplayName("리포트 레코드아이디로 불러오기")
	@Test
	public void getReportByRecordIdTest() {
		//given
		RecordEntity recordEntity = mock(RecordEntity.class);
		ReportEntity reportEntity = ReportEntity.create(recordEntity, "Test Recognition", 3.5);
		Long id = recordEntity.getRecordId();

		//when
		when(reportService.getReportFromRecordId(id)).thenReturn(reportEntity);

		//then
		assertEquals(reportService.getReportFromRecordId(id), reportEntity);
	}

	@DisplayName("리포트 스크립트아이디로 불러오기")
	@Test
	public void getReportByScriptIdTest() {
		//given
		ScriptEntity scriptEntity = mock(ScriptEntity.class);
		RecordEntity recordEntity = mock(RecordEntity.class);
		ReportEntity reportEntity = ReportEntity.create(recordEntity, "Test Recognition", 3.5);
		Long id = scriptEntity.getScriptId();

		//when
		when(reportService.getReportFromRecordId(id)).thenReturn(reportEntity);

		//then
		assertEquals(reportService.getReportFromRecordId(id), reportEntity);
	}

}
