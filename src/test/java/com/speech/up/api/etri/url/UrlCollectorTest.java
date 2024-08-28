package com.speech.up.api.etri.url;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.speech.up.api.etri.type.ApiType;

class UrlCollectorTest {

	@DisplayName("Test UrlCollector with valid URLs")
	@Test
	void testUrlCollectorWithValidUrls() throws MalformedURLException {
		// Given
		String validRecognitionUrl = "http://recognition.example.com/api";
		String validPronunciationUrl = "http://pronunciation.example.com/api";

		// When
		UrlCollector urlCollector = new UrlCollector(validRecognitionUrl, validPronunciationUrl);

		// Then
		assertEquals(new URL(validRecognitionUrl), urlCollector.getApiUrl(ApiType.RECOGNITION),
			"The recognition URL should match the provided URL");
		assertEquals(new URL(validPronunciationUrl), urlCollector.getApiUrl(ApiType.PRONUNCIATION),
			"The pronunciation URL should match the provided URL");
	}

	@DisplayName("Test UrlCollector with invalid URLs")
	@Test
	void testUrlCollectorWithInvalidUrls() {
		// Given
		String invalidUrl = "invalid-url";

		// When & Then
		assertThrows(MalformedURLException.class, () -> new UrlCollector(invalidUrl, "http://valid.example.com/api"),
			"UrlCollector should throw MalformedURLException for an invalid recognition URL");
		assertThrows(MalformedURLException.class, () -> new UrlCollector("http://valid.example.com/api", invalidUrl),
			"UrlCollector should throw MalformedURLException for an invalid pronunciation URL");
	}
}
