package com.speech.up.auth.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomOAuth2UserTest {

	@DisplayName("Test getName method")
	@Test
	void testGetName() {
		// Given
		String userId = "test-user";
		String authorities = "test-authorities";
		CustomOAuth2User customOAuth2User = new CustomOAuth2User(userId, authorities);

		// When
		String name = customOAuth2User.getName();

		// Then
		assertEquals(userId, name, "The getName method should return the userId");
	}

	@DisplayName("Test getAttributes method")
	@Test
	void testGetAttributes() {
		// Given
		CustomOAuth2User customOAuth2User = new CustomOAuth2User();

		// When
		Map<String, Object> attributes = customOAuth2User.getAttributes();

		// Then
		assertNotNull(attributes, "The getAttributes method should return null");
	}

}
