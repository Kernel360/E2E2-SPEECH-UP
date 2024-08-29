package com.speech.up.api.etri.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RequestRecognitionDtoTest {

	@DisplayName("Test creation of RequestRecognitionDto using createRecognition method")
	@Test
	void testCreateRecognition() {
		// Given
		String requestId = "12345";
		String audio = "sample-audio";
		String languageCode = "en";

		// When
		RequestRecognitionDto dto = RequestRecognitionDto.createRecognition(requestId, audio, languageCode);

		// Then
		assertNotNull(dto, "The DTO should not be null");
		assertEquals(requestId, dto.getRequestId(), "Request ID should match the provided value");
		assertNotNull(dto.getArgument(), "ArgumentDTO should not be null");
		assertEquals(audio, dto.getArgument().getAudio(), "Audio should match the provided value");
		assertEquals(languageCode, dto.getArgument().getLanguage_code(), "Language code should match the provided value");
	}

	@DisplayName("Test toString method of RequestRecognitionDto")
	@Test
	void testToString() {
		// Given
		String requestId = "12345";
		String audio = "sample-audio";
		String languageCode = "en";

		// When
		RequestRecognitionDto dto = RequestRecognitionDto.createRecognition(requestId, audio, languageCode);
		String toStringResult = dto.toString();

		// Then
		assertNotNull(toStringResult, "The toString result should not be null");
	}
}
