package com.speech.up.api.etri.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RequestPronunciationDtoTest {

	@DisplayName("Test creation of RequestPronunciationDto using createPronunciation method")
	@Test
	void testCreatePronunciation() {
		// Given
		String requestId = "12345";
		String audio = "sample-audio";
		String script = "sample-script";

		// When
		RequestPronunciationDto dto = RequestPronunciationDto.createPronunciation(requestId, audio, script);

		// Then
		assertNotNull(dto, "The DTO should not be null");
		assertEquals(requestId, dto.getRequestId(), "Request ID should match the provided value");
		assertEquals(audio, dto.getArgument().getAudio(), "Audio should match the provided value");
		assertEquals(script, dto.getArgument().getScript(), "Script should match the provided value");
	}

	@DisplayName("Test toString method of RequestPronunciationDto")
	@Test
	void testToString() {
		// Given
		String requestId = "12345";
		String audio = "sample-audio";
		String script = "sample-script";

		// When
		RequestPronunciationDto dto = RequestPronunciationDto.createPronunciation(requestId, audio, script);
		String toStringResult = dto.toString();

		// Then
		assertNotNull(toStringResult, "The toString result should not be null");
		assertEquals("RequestPronunciationDto(requestId=12345, argument=RequestPronunciationDto.ArgumentDTO(audio=sample-audio, script=sample-script))",
			toStringResult,
			"The toString result should match the expected string representation");
	}
}
