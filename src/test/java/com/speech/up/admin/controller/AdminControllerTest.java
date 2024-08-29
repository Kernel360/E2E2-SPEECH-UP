package com.speech.up.admin.controller;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.speech.up.user.repository.UserRepository;
import com.speech.up.user.service.UserService;
import com.speech.up.user.service.dto.UserGetInfoDto;

import jakarta.servlet.http.HttpServletRequest;

public class AdminControllerTest {

	@Mock
	UserService userService;

	@Mock
	UserRepository userRepository;

	@InjectMocks
	AdminController adminController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("사용자 정보 가져오기")
	@Test
	void getUserTest(){
		//given
		//when
		List<UserGetInfoDto.Response> actualResponse = adminController.getUser();

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("현재 관리자의 사용자 정보")
	@Test
	void checkAdminTest(){
		//given
		HttpServletRequest request = mock(HttpServletRequest.class);

		//when
		ResponseEntity<UserGetInfoDto.Response> actualResponse = adminController.checkAdmin(request);

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("지정된 사용자 ID를 가진 사용자를 삭제")
	@Test
	void dropUserTest(){
		//given
		Long userId = 1L;

		//when
		adminController.dropUser(userId);

		//then
		verify(userRepository, times(1)).deleteById(userId);
	}

	@DisplayName("지정된 사용자 ID를 가진 사용자를 비활성화")
	@Test
	void unUsedUserTest(){
		//given
		Long userId = 1L;

		//when
		adminController.unUsedUser(userId);

		//then
		verify(userService, times(1)).unUsedUser(userId);
	}

	@DisplayName("지정된 사용자 ID를 가진 사용자를 복원")
	@Test
	void restoreUserTest(){
		//given
		Long userId = 1L;

		//when
		adminController.restoreUser(userId);

		//then
		verify(userService, times(1)).restoreUser(userId);
	}
}
