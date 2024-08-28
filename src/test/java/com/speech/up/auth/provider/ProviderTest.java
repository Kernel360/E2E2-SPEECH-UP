package com.speech.up.auth.provider;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.speech.up.auth.service.servicetype.ProviderType;
import com.speech.up.user.entity.UserEntity;

class ProviderTest {

	@Mock
	private OAuth2User oAuth2User;

	private Provider provider;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		provider = new Provider(oAuth2User);
	}

	@DisplayName("카카오 사용자 가져오기")
	@Test
	public void getUserKakaoTest() {
		// given
		UserEntity kakaoUser = mock(UserEntity.class); // 실제 UserEntity 객체 생성
		KakaoProvider kakaoProvider = mock(KakaoProvider.class);
		when(kakaoProvider.getUser()).thenReturn(kakaoUser);

		provider.user.put(ProviderType.KAKAO, kakaoUser);

		// when
		UserEntity result = provider.getUser(ProviderType.KAKAO);

		// then
		assertEquals(kakaoUser, result);
	}

	@DisplayName("깃 허브 사용자 가져오기")
	@Test
	public void getUserGithubTest() {
		// given
		UserEntity githubUser = mock(UserEntity.class); // 실제 UserEntity 객체 생성
		GithubProvider githubProvider = mock(GithubProvider.class);
		when(githubProvider.getUser()).thenReturn(githubUser);

		provider.user.put(ProviderType.GITHUB, githubUser);

		// when
		UserEntity result = provider.getUser(ProviderType.GITHUB);

		// then
		assertEquals(githubUser, result);
	}

	@DisplayName("구글 사용자 가져오기")
	@Test
	public void getUserGoogleTest() {
		// given
		UserEntity googleUser = mock(UserEntity.class); // 실제 UserEntity 객체 생성
		GoogleProvider googleProvider = mock(GoogleProvider.class);
		when(googleProvider.getUser()).thenReturn(googleUser);

		provider.user.put(ProviderType.GOOGLE, googleUser);

		// when
		UserEntity result = provider.getUser(ProviderType.GOOGLE);

		// then
		assertEquals(googleUser, result);
	}

}