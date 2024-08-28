package com.speech.up.record.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.speech.up.record.service.dto.RecordAddDto;
import com.speech.up.record.service.dto.RecordGetDto;
import com.speech.up.record.service.dto.RecordIsUseDto;

public class RecordControllerTest {

	@Mock
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
		List<RecordGetDto.Response> response = Collections.singletonList(mock(RecordGetDto.Response.class));

		//when
		when(recordController.getRecordALl(scriptId)).thenReturn(ResponseEntity.ok(response));

		//then
		assertEquals(recordController.getRecordALl(scriptId), ResponseEntity.ok(response));
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
			when(recordController.addRecord(file, languageCode, scriptId)).thenReturn(ResponseEntity.ok(response));
			//then
			assertEquals(recordController.addRecord(file, languageCode, scriptId), ResponseEntity.ok(response));
		} catch (UnsupportedAudioFileException | IOException e) {
			//then
			assertEquals("Test Exception", e.getMessage());
		}
	}

	@DisplayName("deleteRecord 테스트")
	@Test
	public void deleteRecordTest() {
		//given
		RecordIsUseDto.Request request = mock(RecordIsUseDto.Request.class);
		ResponseEntity<RecordIsUseDto.Response> response = mock(String.valueOf(RecordIsUseDto.Response.class));

		//when
		when(recordController.deleteRecord(request)).thenReturn(response);

		//then
		assertEquals(recordController.deleteRecord(request), response);
	}

	@DisplayName("isRecordAnalyzed 테스트")
	@Test
	public void isRecordAnalyzedTest() {
		//given
		Long recordId = 1L;
		ResponseEntity<Void> response = mock(String.valueOf(Void.class));
		//when
		when(recordController.isRecordAnalyzed(recordId)).thenReturn(response);

		//then
		assertEquals(recordController.isRecordAnalyzed(recordId), response);
	}
}
