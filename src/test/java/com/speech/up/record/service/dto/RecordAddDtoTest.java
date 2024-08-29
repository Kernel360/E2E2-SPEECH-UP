package com.speech.up.record.service.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

import com.speech.up.record.entity.RecordEntity;
import com.speech.up.script.entity.ScriptEntity;

class RecordAddDtoTest {

	private MultipartFile mockFile;
	private ScriptEntity mockScriptEntity;
	private RecordEntity mockRecordEntity;

	@BeforeEach
	void setUp() {
		// Initialize mocks for the tests
		mockFile = mock(MultipartFile.class);
		mockScriptEntity = mock(ScriptEntity.class);
		mockRecordEntity = mock(RecordEntity.class);

		// Set up the mock behavior for RecordEntity
		when(mockRecordEntity.getRecordId()).thenReturn(1L);
		when(mockRecordEntity.getAudio()).thenReturn(new byte[]{1, 2, 3});
		when(mockRecordEntity.getLanguageCode()).thenReturn("en");
		when(mockRecordEntity.getCreatedAt()).thenReturn(LocalDateTime.now());
		when(mockRecordEntity.getScript()).thenReturn(mockScriptEntity);
	}

	@DisplayName("RecordAddDto.Request 생성자 및 getter 테스트")
	@Test
	void testRequestDto() {
		String languageCode = "en";
		RecordAddDto.Request requestDto = new RecordAddDto.Request(mockFile, languageCode, mockScriptEntity);

		// Validate the Request DTO properties
		assertEquals(mockFile, requestDto.getFile());
		assertEquals(languageCode, requestDto.getLanguageCode());
		assertEquals(mockScriptEntity, requestDto.getScriptEntity());
	}

	@DisplayName("RecordAddDto.Response 생성자 및 getter 테스트")
	@Test
	void testResponseDto() {
		RecordAddDto.Response responseDto = new RecordAddDto.Response(mockRecordEntity);

		// Validate the Response DTO properties
		assertEquals(1L, responseDto.getRecordId());
		assertEquals("en", responseDto.getLanguageCode());
		assertEquals(mockRecordEntity.getCreatedAt(), responseDto.getCreatedAt());
		assertEquals(mockScriptEntity, responseDto.getScriptId());
	}

	@DisplayName("RecordAddDto.toResponse() 메서드 테스트")
	@Test
	void testToResponse() {
		RecordAddDto.Response responseDto = RecordAddDto.toResponse(mockRecordEntity);

		// Validate the Response DTO properties via the static method
		assertEquals(1L, responseDto.getRecordId());
		assertEquals("en", responseDto.getLanguageCode());
		assertEquals(mockRecordEntity.getCreatedAt(), responseDto.getCreatedAt());
		assertEquals(mockScriptEntity, responseDto.getScriptId());
	}
}
