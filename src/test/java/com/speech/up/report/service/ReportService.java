package com.speech.up.report.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.speech.up.record.repository.RecordRepository;
import com.speech.up.report.repository.ReportRepository;

@ExtendWith(MockitoExtension.class)
public class ReportService {
	@Mock
	private ReportRepository reportRepository;

	@Mock
	private RecordRepository recordRepository;

	@InjectMocks
	private ReportService reportService;

	//ReportEntity 의 객체가 잘 담기고 save 메소드가 호출되었는지 확인
	@Test
	public void saveReport(){
		//given
		//when
		//then
	}



}
