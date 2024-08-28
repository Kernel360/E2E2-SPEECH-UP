package com.speech.up.api.etri.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.speech.up.api.etri.service.VoiceToTextService;

public class ETRIApiControllerTest {
	@Mock
	VoiceToTextService voiceToTextService;

	@InjectMocks
	ETRIApiController etriApiController;


	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("api 호출하기")
	@Test
	void createTaskTest(){
		//given
		String script = "script";
		String recordId = "1";

		//when
		etriApiController.createTask(script, recordId);

		//then
		verify(voiceToTextService, times(1)).callRecognitionApi(script, Long.valueOf(recordId));
	}
}
