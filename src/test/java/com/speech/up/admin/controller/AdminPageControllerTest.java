package com.speech.up.admin.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class AdminPageControllerTest {

	@InjectMocks
	AdminPageController adminPageController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("admin 페이지 이동")
	@Test
	void adminPage() {
		String actualResponse = adminPageController.adminPage();

		assertNotNull(actualResponse);
	}
}
