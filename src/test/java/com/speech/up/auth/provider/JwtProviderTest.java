package com.speech.up.auth.provider;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.http.HttpServletRequest;

class JwtProviderTest {
	@InjectMocks
	private JwtProvider jwtProvider;

	@Mock
	private HttpServletRequest request;

	@BeforeEach
	public void setUp() throws IllegalAccessException, NoSuchFieldException {

		MockitoAnnotations.openMocks(this);
		String scretKey = "thdwIweecwetweyFtefdfdasqwqwsdct";

		Field unOpenField = JwtProvider.class.getDeclaredField("secretKey");
		unOpenField.setAccessible(true);
		unOpenField.set(jwtProvider, scretKey);

	}

	@DisplayName("토큰 생성 테스트")
	@Test
	public void createTokenTest() {
		// given
		String userId = "testUser";

		// when
		String token = jwtProvider.createToken(userId);

		// then
		assertNotNull(token);
	}

	@DisplayName("validate 테스트")
	@Test
	public void validateTest() {
		// given
		String userId = "testUser";

		//when
		String token = jwtProvider.createToken(userId);
		String validatedUserId = jwtProvider.validate(token);

		// then
		assertEquals(userId, validatedUserId);
	}

	@DisplayName("헤더 가져오기 테스트")
	@Test
	public void getHeaderTest() {
		// given
		String userId = "testUser";
		String token = jwtProvider.createToken(userId);

		given(request.getHeader("Authorization")).willReturn("Bearer " + token);

		// when
		String validatedUserId = jwtProvider.getHeader(request);

		// then
		assertEquals(userId, validatedUserId);
	}

}