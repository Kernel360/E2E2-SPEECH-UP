package com.speech.up.auth.filter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.security.core.context.SecurityContextHolder;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.speech.up.auth.provider.JwtProvider;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;

import jakarta.servlet.ServletException;

class JwtAuthenticationFilterTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private JwtProvider jwtProvider;

	@InjectMocks
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private MockFilterChain filterChain;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		filterChain = new MockFilterChain();
		SecurityContextHolder.clearContext();
	}

	@DisplayName("유효한 JWT 토큰이 있는 경우 인증 설정 확인")
	@Test
	void testDoFilterInternal_withValidToken() throws ServletException, IOException {
		// Given
		String token = "valid-token";
		String socialId = "test-social-id";
		String role = "ROLE_USER";

		request.addHeader("Authorization", "Bearer " + token);

		when(jwtProvider.validate(token)).thenReturn(socialId);
		UserEntity userEntity = mock(UserEntity.class);
		when(userEntity.getAuthorization()).thenReturn(role);
		when(userRepository.findBySocialId(socialId)).thenReturn(Optional.of(userEntity));

		// When
		jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

		// Then
		assertNotNull(SecurityContextHolder.getContext().getAuthentication());
		assertEquals(socialId, SecurityContextHolder.getContext().getAuthentication().getName());
		assertEquals(token, SecurityContextHolder.getContext().getAuthentication().getCredentials());
		assertTrue(SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
			.anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role)));

		verify(jwtProvider, times(1)).validate(token);
		verify(userRepository, times(1)).findBySocialId(socialId);
	}

	@DisplayName("JWT 토큰이 없거나 유효하지 않은 경우 필터 체인만 호출")
	@Test
	void testDoFilterInternal_withoutValidToken() throws ServletException, IOException {
		// Given
		String invalidToken = "invalid-token";

		request.addHeader("Authorization", "Bearer " + invalidToken);

		when(jwtProvider.validate(invalidToken)).thenReturn(null);

		// When
		jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

		// Then
		assertNull(SecurityContextHolder.getContext().getAuthentication());

		verify(jwtProvider, times(1)).validate(invalidToken);
		verify(userRepository, times(0)).findBySocialId(anyString());
	}

	@DisplayName("JWT 토큰이 없을 때 필터 체인만 호출")
	@Test
	void testDoFilterInternal_noToken() throws ServletException, IOException {
		// Given
		// No token in the request

		// When
		jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

		// Then
		assertNull(SecurityContextHolder.getContext().getAuthentication());

		verify(jwtProvider, times(0)).validate(anyString());
		verify(userRepository, times(0)).findBySocialId(anyString());
	}
}
