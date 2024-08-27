package com.speech.up.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.speech.up.user.service.dto.UserGetInfoDto;

import jakarta.servlet.http.HttpServletRequest;

public class UserServiceTest {
	@Mock
	UserService userService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("모든 유저 검색")
	@Test
	public void getAllUserTest() {
		//given
		List<UserGetInfoDto.Response> response = Collections.singletonList(mock(UserGetInfoDto.Response.class));

		//when
		when(userService.getAllUsers()).thenReturn(response);

		//then
		assertEquals(userService.getAllUsers(), response);
	}

	@DisplayName("나의 정보 검색")
	@Test
	public void getUserByToken() {
		//given
		HttpServletRequest request = mock(HttpServletRequest.class);
		UserGetInfoDto.Response response = mock(UserGetInfoDto.Response.class);

		//when
		when(userService.getUserInfo(request)).thenReturn(response);

		//then
		assertEquals(userService.getUserInfo(request), response);
	}

	@DisplayName("나의 유저 정보 삭제")
	@Test
	public void deleteUserByToken() {
		//given
		HttpServletRequest hasAuthorizationRequest = mock(HttpServletRequest.class);
		HttpServletRequest noHasAuthorizationRequest = mock(HttpServletRequest.class);
		hasAuthorizationRequest.setAttribute("Authorization", "Bearer token");

		//when
		userService.deleteUser(hasAuthorizationRequest);

		//then
		verify(userService, times(1)).deleteUser(hasAuthorizationRequest);
		assertNull(userService.getUserInfo(noHasAuthorizationRequest));
	}

	@DisplayName("나의 유저 정보 정지")
	@Test
	public void bannedUserByToken() {
		//given
		Long userId = 1L;

		//when
		userService.unUsedUser(userId);

		// then
		verify(userService, times(1)).unUsedUser(userId);
	}

	@DisplayName("나의 유저 정보 복구")
	@Test
	public void restoreUserByToken() {
		//given
		Long userId = 1L;

		//when
		userService.restoreUser(userId);

		// then
		verify(userService, times(1)).restoreUser(userId);
	}

}
