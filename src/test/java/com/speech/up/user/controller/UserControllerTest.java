package com.speech.up.user.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.speech.up.user.service.dto.UserGetInfoDto;

import jakarta.servlet.http.HttpServletRequest;

public class UserControllerTest {
	@Mock
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
		UserGetInfoDto.Response response = mock(UserGetInfoDto.Response.class);

		//when
		when(userController.getUserInfo(request)).thenReturn(ResponseEntity.ok(response));

		//then
		assertEquals(userController.getUserInfo(request), ResponseEntity.ok(response));
	}

	@DisplayName("deleteUser 테스트")
	@Test
	public void deleteUserTest() {
		//given
		HttpServletRequest request = mock(HttpServletRequest.class);

		//when
		userController.deleteUser(request);

		//then
		verify(userController, times(1)).deleteUser(request);
	}
}
