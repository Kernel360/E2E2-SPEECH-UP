package com.speech.up.record.service;

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
import org.springframework.web.multipart.MultipartFile;

import com.speech.up.record.service.dto.RecordAddDto;
import com.speech.up.record.service.dto.RecordGetDto;
import com.speech.up.record.service.dto.RecordIsUseDto;

public class RecordServiceTest {

	@Mock
	RecordService recordService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("스크립트 아이디로 녹음 데이터 리스트 가져오기")
	@Test
	public void getRecordListByScriptIdTest() {
		//given
		List<RecordGetDto.Response> response = Collections.singletonList(mock(RecordGetDto.Response.class));
		Long scriptId = 1L;

		//when
		when(recordService.getRecordList(scriptId)).thenReturn(response);

		//then
		assertEquals(response, recordService.getRecordList(scriptId));
	}

	@DisplayName("녹음 데이터 추가하기")
	@Test
	public void addRecordTest() {
		//given
		MultipartFile file = mock(MultipartFile.class);
		String languageCode = "korean";
		Long scriptId = 1L;
		RecordAddDto.Response response = mock(RecordAddDto.Response.class);

		try {
		//when
			when(recordService.addRecord(file, languageCode, scriptId)).thenReturn(response);
			//then
			assertEquals(response, recordService.addRecord(file, languageCode, scriptId));
		} catch (UnsupportedAudioFileException | IOException e) {
			//then
			assertEquals("Test Exception", e.getMessage());
		}
	}

	@DisplayName("녹음 데이터 삭제하기")
	@Test
	public void deleteRecordTest() {
		//given
		RecordIsUseDto.Request request = mock(RecordIsUseDto.Request.class);
		RecordIsUseDto.Response response = mock(RecordIsUseDto.Response.class);

		//when
		when(recordService.deleteRecord(request)).thenReturn(response);

		//then
		assertEquals(response, recordService.deleteRecord(request));
	}

	@DisplayName("녹음 아이디로 분석 메서드 호출하기")
	@Test
	public void callAnalyzeMethodByRecordIdTest() {
		//given
		Long recordId = 1L;

		//when
		recordService.analyzed(recordId);

		//then
		verify(recordService, times(1)).analyzed(recordId);
	}
}
