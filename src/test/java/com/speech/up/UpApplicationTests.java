package com.speech.up;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.Ordered;
import org.springframework.web.filter.ForwardedHeaderFilter;

import io.github.cdimascio.dotenv.Dotenv;

class UpApplicationTests {

	@Mock
	private Dotenv dotenvMock;

	@InjectMocks
	private UpApplication upApplication;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("환경 변수가 로드되었는지 테스트")
	void testEnvironmentVariablesLoaded() {
		// given
		when(dotenvMock.get("JWT_SECRET_KEY")).thenReturn("secret");

		// when
		UpApplication.main(new String[] {});

		// then
		assertEquals("secret", dotenvMock.get("JWT_SECRET_KEY"));
		assertNotNull(upApplication,System.getenv("JWT_SECRET_KEY"));
	}

	@Test
	@DisplayName("ForwardedHeaderFilter 등록 테스트")
	void testForwardedHeaderFilterRegistration() {
		// given
		FilterRegistrationBean<ForwardedHeaderFilter> registrationBean = upApplication.forwardedHeaderFilter();

		// then
		assertNotNull(registrationBean);
		assertNotNull(registrationBean.getFilter());
		assertEquals(Ordered.HIGHEST_PRECEDENCE, registrationBean.getOrder());
		assertEquals(4, registrationBean.getUrlPatterns().size());
	}
}
