package com.speech.up.api.etri.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApiTypeTest {

	@DisplayName("Test enum values")
	@Test
	void testEnumValues() {
		// Given
		ApiType[] expectedValues = {ApiType.RECOGNITION, ApiType.PRONUNCIATION};

		// When
		ApiType[] actualValues = ApiType.values();

		// Then
		assertEquals(expectedValues.length, actualValues.length, "Enum values length should match");
		for (int i = 0; i < expectedValues.length; i++) {
			assertEquals(expectedValues[i], actualValues[i], "Enum value at index " + i + " should match");
		}
	}

	@DisplayName("Test enum valueOf method")
	@Test
	void testValueOf() {
		// Given
		String recognitionName = "RECOGNITION";
		String pronunciationName = "PRONUNCIATION";

		// When & Then
		assertEquals(ApiType.RECOGNITION, ApiType.valueOf(recognitionName), "Enum value should match RECOGNITION");
		assertEquals(ApiType.PRONUNCIATION, ApiType.valueOf(pronunciationName), "Enum value should match PRONUNCIATION");
	}

	@DisplayName("Test enum valueOf with invalid name")
	@Test
	void testValueOfWithInvalidName() {
		// Given
		String invalidName = "INVALID";

		// When & Then
		assertThrows(IllegalArgumentException.class, () -> ApiType.valueOf(invalidName),
			"Enum.valueOf should throw IllegalArgumentException for invalid name");
	}
}
