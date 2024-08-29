package com.speech.up.api.etri.url;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PronunciationAITest {

	@DisplayName("Test constructor and URL creation")
	@Test
	void testConstructorAndGetUrl() {
		// Given
		String validUrlString = "http://example.com/api";
		PronunciationAI pronunciationAI = new PronunciationAI(validUrlString);

		// When
		URL url;
		try {
			url = pronunciationAI.getUrl();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}

		// Then
		assertEquals(validUrlString, url.toString(), "The URL string should match the input string");
	}

	@DisplayName("Test invalid URL")
	@Test
	void testGetUrlWithInvalidUrl() {
		// Given
		String invalidUrlString = "invalid-url";
		PronunciationAI pronunciationAI = new PronunciationAI(invalidUrlString);

		// When & Then
		assertThrows(MalformedURLException.class, pronunciationAI::getUrl,
			"getUrl should throw MalformedURLException for an invalid URL");
	}
}
