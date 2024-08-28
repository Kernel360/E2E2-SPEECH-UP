package com.speech.up.api.etri.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResponsePronunciationApiDtoTest {

	@DisplayName("Test creation and getters of ResponsePronunciationApiDto")
	@Test
	void testDtoCreationAndGetters() {
		// Given
		String requestId = "req-123";
		int result = 1;
		String recognized = "recognized-text";
		Double score = 95.5;

		ResponsePronunciationApiDto.ReturnObjectDTO returnObjectDTO = new ResponsePronunciationApiDto.ReturnObjectDTO();
		ResponsePronunciationApiDto dto = new ResponsePronunciationApiDto(requestId, result, returnObjectDTO);

		// When & Then
		assertNotNull(dto, "The DTO should not be null");
		assertEquals(requestId, dto.getRequest_id(), "Request ID should match the provided value");
		assertEquals(result, dto.getResult(), "Result should match the provided value");
		assertNotNull(dto.getReturn_object(), "ReturnObjectDTO should not be null");
	}

	@DisplayName("Test toString method of ResponsePronunciationApiDto")
	@Test
	void testToString() {
		// Given
		String requestId = "req-123";
		int result = 1;
		String recognized = "recognized-text";
		Double score = 95.5;

		ResponsePronunciationApiDto.ReturnObjectDTO returnObjectDTO = new ResponsePronunciationApiDto.ReturnObjectDTO();
		ResponsePronunciationApiDto dto = new ResponsePronunciationApiDto(requestId, result, returnObjectDTO);

		// When
		String toStringResult = dto.toString();

		// Then
		assertNotNull(toStringResult, "The toString result should not be null");
	}
}
