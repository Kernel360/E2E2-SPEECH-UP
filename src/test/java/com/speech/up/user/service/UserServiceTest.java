package com.speech.up.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.speech.up.auth.provider.JwtProvider;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;
import com.speech.up.user.service.dto.UserGetInfoDto;

import jakarta.servlet.http.HttpServletRequest;

public class UserServiceTest {
	@Mock
	UserRepository userRepository;
	@Mock
	JwtProvider jwtProvider;
	@Mock
	UserEntity mockUserEntity;

	@InjectMocks
	UserService userService;

	Long userId;
	String socialId;
	LocalDateTime lastAccessedAt;
	String email;
	String name;
	String level;
	String authorization;
	String providerType;
	boolean isUse;

	UserEntity expectedEntity;
	HttpServletRequest servletRequest;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		userId = 1L;
		socialId = "socialId";
		lastAccessedAt = LocalDateTime.now();
		email = "email";
		name = "name";
		level = "level";
		authorization = "authorization";
		providerType = "providerType";
		isUse = true;

		mockUserEntity = mock(UserEntity.class);
		when(mockUserEntity.getUserId()).thenReturn(userId);
		when(mockUserEntity.getSocialId()).thenReturn(socialId);
		when(mockUserEntity.getLastAccessedAt()).thenReturn(lastAccessedAt);
		when(mockUserEntity.getEmail()).thenReturn(email);
		when(mockUserEntity.getName()).thenReturn(name);
		when(mockUserEntity.getLevel()).thenReturn(level);
		when(mockUserEntity.getProviderType()).thenReturn(providerType);
		when(mockUserEntity.isUse()).thenReturn(isUse);

		when(userRepository.findBySocialId(socialId)).thenReturn(Optional.of(mockUserEntity));
		when(userRepository.findById(userId)).thenReturn(Optional.of(mockUserEntity));
		when(userRepository.existsBySocialId(socialId)).thenReturn(true);
		when(userRepository.findAllByLastAccessedAtBeforeAndIsUseTrue(lastAccessedAt)).thenReturn(
			Collections.singletonList(mockUserEntity));

		servletRequest = mock(HttpServletRequest.class);

		expectedEntity = UserEntity.updateUserAccess(mockUserEntity);
	}

	@DisplayName("모든 유저 검색")
	@Test
	public void getAllUserTest() {
		//given & when
		List<UserGetInfoDto.Response> actualResponse = userService.getAllUsers();

		//then
		verify(userRepository, times(1)).findAll();
		assertNotNull(actualResponse);
	}

	@DisplayName("나의 정보 검색")
	@Test
	public void getUserByToken() {
		//given

		//when
		when(jwtProvider.getHeader(servletRequest)).thenReturn(socialId);
		UserGetInfoDto.Response actualResponse = userService.getUserInfo(servletRequest);

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("나의 유저 정보 삭제")
	@Test
	public void deleteUserByToken() {
		//given & when
		when(jwtProvider.getHeader(servletRequest)).thenReturn(socialId);
		userService.deleteUser(servletRequest);

		//then
		verify(userRepository, times(1)).deleteBySocialId(socialId);
	}

	@DisplayName("나의 유저 정보 정지")
	@Test
	public void bannedUserByToken() {
		//given & when
		userService.unUsedUser(userId);

		// then
		assertNotNull(userRepository.findById(userId));
	}

	@DisplayName("나의 유저 정보 복구")
	@Test
	public void restoreUserByToken() {
		//given & when
		userService.restoreUser(userId);

		// then
		assertNotNull(userRepository.findById(userId));
	}

}
