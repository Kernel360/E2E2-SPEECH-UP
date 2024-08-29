package com.speech.up;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.Ordered;
import org.springframework.web.filter.ForwardedHeaderFilter;

import io.github.cdimascio.dotenv.Dotenv;

class UpApplicationTests {

	private static Dotenv dotenv;

	@InjectMocks
	private UpApplication upApplication;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	@BeforeAll
	public static void setup() {
		try {
			dotenv = Dotenv.load(); // .env 파일을 로드
		} catch (Exception e) {
			throw new RuntimeException("Failed to load .env file", e);
		}
	}
	@Test
	@DisplayName("실행 테스트")
	void testEnvironmentVariablesLoaded() {
		// given
		// when
		UpApplication.main(new String[] {});

		// then
		assertNotNull(upApplication);
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
