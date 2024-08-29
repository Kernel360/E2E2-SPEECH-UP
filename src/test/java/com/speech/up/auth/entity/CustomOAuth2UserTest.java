package com.speech.up.auth.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

class CustomOAuth2UserTest {

	@DisplayName("Test getName method")
	@Test
	void testGetName() {
		// Given
		String userId = "test-user";
		CustomOAuth2User customOAuth2User = new CustomOAuth2User(userId);

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
		assertNull(attributes, "The getAttributes method should return null");
	}

	@DisplayName("Test getAuthorities method")
	@Test
	void testGetAuthorities() {
		// Given
		CustomOAuth2User customOAuth2User = new CustomOAuth2User();

		// When
		Collection<? extends GrantedAuthority> authorities = customOAuth2User.getAuthorities();

		// Then
		assertNull(authorities, "The getAuthorities method should return null");
	}
}
