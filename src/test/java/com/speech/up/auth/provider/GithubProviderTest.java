package com.speech.up.auth.provider;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.speech.up.auth.service.servicetype.LevelType;
import com.speech.up.auth.service.servicetype.ProviderType;
import com.speech.up.common.enums.UserStatusCode;
import com.speech.up.user.entity.UserEntity;

class GithubProviderTest {
	@Mock
	private OAuth2User oAuth2User;

	private GithubProvider githubProvider;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		Map<String, Object> attributes = new HashMap<>();
		attributes.put("id", "12345");
		attributes.put("name", "Test User");

		when(oAuth2User.getAttributes()).thenReturn(attributes);

		githubProvider = new GithubProvider(oAuth2User);
	}

	@DisplayName("유저 정보 가져오기")
	@Test
	public void getUserTest() {
		// given
		UserEntity user = githubProvider.getUser();

		// when & then
		assertEquals("12345", user.getSocialId());
		assertEquals("none", user.getEmail());
		assertEquals(LevelType.BRONZE.name(), user.getLevel());
		assertEquals("Test User", user.getName());
		assertEquals(UserStatusCode.ROLE_GENERAL_USER.name(), user.getAuthorization());
		assertEquals(ProviderType.GITHUB.name(), user.getProviderType());
	}


}