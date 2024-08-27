package com.speech.up.report.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.speech.up.record.entity.RecordEntity;
import com.speech.up.report.entity.ReportEntity;
import com.speech.up.script.entity.ScriptEntity;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
	@Mock
	private ReportService reportService;

	@DisplayName("리포트 저장하기")
	@Test
	public void saveReportTest() {
		//given
		RecordEntity reportEntity = mock(RecordEntity.class);
		String recognized = "recognized";
		double score = 1.0;

		//when
		reportService.saveReport(reportEntity, recognized, score);

		//then
		verify(reportService, times(1)).saveReport(reportEntity, recognized, score);
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
