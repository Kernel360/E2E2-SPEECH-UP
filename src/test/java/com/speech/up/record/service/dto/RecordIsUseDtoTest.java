package com.speech.up.record.service.dto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.speech.up.record.entity.RecordEntity;

class RecordIsUseDtoTest {

	private RecordEntity mockRecordEntity;

	@BeforeEach
	void setUp() {
		// Initialize mock for the tests
		mockRecordEntity = mock(RecordEntity.class);

		// Set up the mock behavior for RecordEntity
		when(mockRecordEntity.getRecordId()).thenReturn(1L);
		when(mockRecordEntity.isUse()).thenReturn(true);
	}

	@DisplayName("RecordIsUseDto.Request 생성자 및 getter 테스트")
	@Test
	void testRequestDto() {
		RecordIsUseDto.Request requestDto = new RecordIsUseDto.Request(mockRecordEntity);
		when(mockRecordEntity.getRecordId()).thenReturn(1L);
		when(mockRecordEntity.isUse()).thenReturn(true);

		// Validate the Request DTO properties
		assertEquals(1L, requestDto.getRecordId());
		assertEquals(true, requestDto.isUse());
	}

	@DisplayName("RecordIsUseDto.Response 생성자 및 getter 테스트")
	@Test
	void testResponseDto() {
		RecordIsUseDto.Response responseDto = new RecordIsUseDto.Response(mockRecordEntity);

		// Validate the Response DTO properties
		assertEquals(1L, responseDto.getRecordId());
		assertTrue(responseDto.isUse());
	}

	@DisplayName("RecordIsUseDto.toResponse() 메서드 테스트")
	@Test
	void testToResponse() {
		RecordIsUseDto.Response responseDto = RecordIsUseDto.toResponse(mockRecordEntity);

		// Validate the Response DTO properties via the static method
		assertEquals(1L, responseDto.getRecordId());
		assertTrue(responseDto.isUse());
	}
}
