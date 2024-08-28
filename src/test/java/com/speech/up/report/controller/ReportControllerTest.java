package com.speech.up.report.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.speech.up.demo.ReportPageController;

public class ReportControllerTest {

	@Mock
	ReportPageController reportPageController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}


	@DisplayName("getReport 테스트")
	@Test
	public void getReportTest() {
		//given
		Long recordId = 1L;

		//when
		//then
	}
}

