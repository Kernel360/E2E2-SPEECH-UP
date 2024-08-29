package com.speech.up.api.etri.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResponseRecognitionDtoTest {

	@DisplayName("Test creation and getters of ResponseRecognitionDto")
	@Test
	void testDtoCreationAndGetters() {
		// Given
		String requestId = "req-456";
		int result = 2;
		String recognized = "recognized-text";

		ResponseRecognitionDto.ReturnObjectDTO returnObjectDTO = new ResponseRecognitionDto.ReturnObjectDTO();
		ResponseRecognitionDto dto = new ResponseRecognitionDto(requestId, result, returnObjectDTO);

		// When & Then
		assertNotNull(dto, "The DTO should not be null");
		assertEquals(requestId, dto.getRequest_id(), "Request ID should match the provided value");
		assertEquals(result, dto.getResult(), "Result should match the provided value");
		assertNotNull(dto.getReturn_object(), "ReturnObjectDTO should not be null");
	}

	@DisplayName("Test toString method of ResponseRecognitionDto")
	@Test
	void testToString() {
		// Given
		String requestId = "req-456";
		int result = 2;
		String recognized = "recognized-text";

		ResponseRecognitionDto.ReturnObjectDTO returnObjectDTO = new ResponseRecognitionDto.ReturnObjectDTO();
		ResponseRecognitionDto dto = new ResponseRecognitionDto(requestId, result, returnObjectDTO);

		// When
		String toStringResult = dto.toString();

		// Then
		assertNotNull(toStringResult, "The toString result should not be null");
	}
}
