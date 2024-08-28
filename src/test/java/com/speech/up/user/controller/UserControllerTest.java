package com.speech.up.user.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.speech.up.user.service.UserService;
import com.speech.up.user.service.dto.UserGetInfoDto;

import jakarta.servlet.http.HttpServletRequest;

public class UserControllerTest {
	@Mock
	UserService userService;

	@InjectMocks
	UserController userController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("getUserInfo 테스트")
	@Test
	public void getUserInfoTest() {
		//given
		HttpServletRequest request = mock(HttpServletRequest.class);

		//when
		ResponseEntity<UserGetInfoDto.Response> actualResponse = userController.getUserInfo(request);

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("deleteUser 테스트")
	@Test
	public void deleteUserTest() {
		//given
		HttpServletRequest request = mock(HttpServletRequest.class);

		//when
		userController.deleteUser(request);

		//then
		verify(userService, times(1)).deleteUser(request);
	}
}
