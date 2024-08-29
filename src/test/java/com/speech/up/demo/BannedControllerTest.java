package com.speech.up.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class BannedControllerTest {
	@InjectMocks
	BannedController bannedController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("banned-page 테스트")
	@Test
	void bannedPageTest() {
		//given
		String expected = "banned";

		//when
		String actualResponse = bannedController.bannedPage();

		assertEquals(expected, actualResponse);
	}

}
