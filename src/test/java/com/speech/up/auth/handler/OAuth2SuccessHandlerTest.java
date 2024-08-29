package com.speech.up.auth.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import com.speech.up.auth.entity.CustomOAuth2User;
import com.speech.up.auth.provider.JwtProvider;

class OAuth2SuccessHandlerTest {

	@Mock
	private JwtProvider jwtProvider;

	@Mock
	private Authentication authentication;

	@Mock
	private CustomOAuth2User customOAuth2User;

	@InjectMocks
	private OAuth2SuccessHandler oAuth2SuccessHandler;

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();

		ReflectionTestUtils.setField(oAuth2SuccessHandler, "allowUrl", "http://localhost:8080/redirect");
	}

	@DisplayName("OAuth2 인증 성공 시 JWT 토큰 생성 및 리다이렉트")
	@Test
	void testOnAuthenticationSuccess() throws IOException {
		// Given
		String socialId = "test-social-id";
		String token = "test-token";

		when(authentication.getPrincipal()).thenReturn(customOAuth2User);
		when(customOAuth2User.getName()).thenReturn(socialId);
		when(jwtProvider.createToken(socialId)).thenReturn(token);

		// When
		oAuth2SuccessHandler.onAuthenticationSuccess(request, response, authentication);

		// Then
		String expectedRedirectUrl = "http://localhost:8080/redirect?token=Bearer test-token";
		assertEquals(expectedRedirectUrl, response.getRedirectedUrl(), "리다이렉트 URL이 예상과 일치해야 합니다.");
	}
}
