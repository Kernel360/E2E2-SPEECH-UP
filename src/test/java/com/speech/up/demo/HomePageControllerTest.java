package com.speech.up.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

class HomePageControllerTest {

	@InjectMocks
	HomePageController homePageController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("홈페이지를 반환합니다.")
	@Test
	void homeTest() {
		String actualResponse = homePageController.home();

		assertNotNull(actualResponse);
		assertEquals("home", actualResponse);
	}

	@DisplayName("로그인 페이지를 반환합니다.")
	@Test
	void loginTest() {
		String actualResponse = homePageController.login();

		assertNotNull(actualResponse);
		assertEquals("signIn", actualResponse);
	}

	@DisplayName("사용자 개인 페이지를 반환합니다.")
	@Test
	void myPageTest() {
		String actualResponse = homePageController.myPage();

		assertNotNull(actualResponse);
		assertEquals("myPage", actualResponse);
	}

	@DisplayName("지도 페이지를 반환합니다.")
	@Test
	void mapPage() {
		Model model = mock(Model.class);
		String actualResponse = homePageController.mapPage(model);

		assertNotNull(actualResponse);
		assertEquals("map", actualResponse);
	}
}