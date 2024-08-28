package com.speech.up.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class ScriptPageControllerTest {
	@InjectMocks
	ScriptPageController scriptPageController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("스크립트 페이지를 반환합니다.")
	@Test
	void scriptTest() {
		String actual = scriptPageController.script();

		assertNotNull(actual);
		assertEquals("script", actual);
	}

	@DisplayName("스크립트 목록 페이지를 반환합니다.")
	@Test
	void scriptListTest() {
		String actual = scriptPageController.scriptList();

		assertNotNull(actual);
		assertEquals("script-list", actual);
	}

	@DisplayName("스크립트 작성 페이지를 반환합니다.")
	@Test
	void scriptWriteTest() {
		String actual = scriptPageController.scriptWrite();

		assertNotNull(actual);
		assertEquals("script-write", actual);
	}
}