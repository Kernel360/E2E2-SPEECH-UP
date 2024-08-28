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

class KakaoProviderTest {

	@Mock
	private OAuth2User oAuth2User;

	private KakaoProvider kakaoProvider;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		Map<String, Object> attributes = new HashMap<>();
		attributes.put("id", "kakao-12345");

		Map<String, String> properties = new HashMap<>();
		properties.put("nickname", "Test User");

		attributes.put("properties", properties);
		when(oAuth2User.getAttributes()).thenReturn(attributes);

		kakaoProvider = new KakaoProvider(oAuth2User);
	}

	@Test
	public void getUserTest() {
		// given
		UserEntity user = kakaoProvider.getUser();

		// when & then
		assertEquals("kakao-12345", user.getSocialId());
		assertEquals("none", user.getEmail());
		assertEquals(LevelType.BRONZE.name(), user.getLevel());
		assertEquals("Test User", user.getName());
		assertEquals(UserAuthorizationType.ROLE_GENERAL_USER.name(), user.getAuthorization());
		assertEquals(ProviderType.KAKAO.name(), user.getProviderType());
	}


}