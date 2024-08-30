package com.speech.up.record.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.speech.up.record.service.RecordService;
import com.speech.up.record.service.dto.RecordAddDto;
import com.speech.up.record.service.dto.RecordGetDto;
import com.speech.up.record.service.dto.RecordIsUseDto;

public class RecordControllerTest {

	@Mock
	RecordService recordService;

	@InjectMocks
	RecordController recordController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("getRecordAll 테스트")
	@Test
	public void getRecordAllTest() {
		//given
		Long scriptId = 1L;

		//when
		ResponseEntity<List<RecordGetDto.Response>> actualResponse = recordController.getRecordALl(scriptId);

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("addRecord 테스트")
	@Test
	public void addRecordTest() {
		//given
		RecordAddDto.Response response = mock(RecordAddDto.Response.class);
		MultipartFile file = mock(MultipartFile.class);
		String languageCode = "korean";
		Long scriptId = 1L;

		//when
		try {
			ResponseEntity<RecordAddDto.Response> actualResponse = recordController.addRecord(file, languageCode,
				scriptId);

			//then
			assertNotNull(actualResponse);
		} catch (UnsupportedAudioFileException | IOException e) {
			//then
			assertEquals("Test Exception", e.getMessage());
		}
	}

	@DisplayName("deleteRecord 테스트")
	@Test
	public void deleteRecordTest() {
		//given
		Long recordId = 1L;

		//when
		ResponseEntity<RecordIsUseDto.Response> actualResponse = recordController.deleteRecord(recordId);

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("isRecordAnalyzed 테스트")
	@Test
	public void isRecordAnalyzedTest() {
		//given
		Long recordId = 1L;
		//when
		ResponseEntity<Void> actualResponse = recordController.isRecordAnalyzed(recordId);
		//then
		assertNotNull(actualResponse);
	}
}
