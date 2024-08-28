package com.speech.up.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class RegisterPageControllerTest {
	@InjectMocks
	RegisterPageController registerPageController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("사용자 등록 페이지를 반환합니다.")
	@Test
	void signUpTest() {
		String actual = registerPageController.signUp();

		assertNotNull(actual);
		assertEquals("signIn", actual);
	}
}