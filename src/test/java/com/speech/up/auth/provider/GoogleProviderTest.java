package com.speech.up.auth.provider;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.speech.up.auth.service.implement.UserAuthorizationType;
import com.speech.up.auth.service.servicetype.LevelType;
import com.speech.up.auth.service.servicetype.ProviderType;
import com.speech.up.user.entity.UserEntity;

class GoogleProviderTest {
	@Mock
	private OAuth2User oAuth2User;

	private GoogleProvider googleProvider;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		Map<String, Object> attributes = new HashMap<>();
		attributes.put("sub", "google-12345");
		attributes.put("email", "testuser@gmail.com");
		attributes.put("name", "Test User");

		when(oAuth2User.getAttributes()).thenReturn(attributes);

		googleProvider = new GoogleProvider(oAuth2User);
	}

	@Test
	public void getUserTest() {
		//given
		UserEntity user = googleProvider.getUser();

		//when & then
		assertEquals("google-12345", user.getSocialId());
		assertEquals("testuser@gmail.com", user.getEmail());
		assertEquals(LevelType.BRONZE.name(), user.getLevel());
		assertEquals("Test User", user.getName());
		assertEquals(UserAuthorizationType.ROLE_GENERAL_USER.name(), user.getAuthorization());
		assertEquals(ProviderType.GOOGLE.name(), user.getProviderType());
	}


}