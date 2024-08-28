package com.speech.up.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;

@SpringJUnitConfig
public class UserSchedulingTest {

	@Mock
	UserRepository userRepository;

	@Mock
	 UserService userService;

	@InjectMocks
	private UserScheduling userScheduling;

	LocalDateTime oneWeekAgo;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		// 고정된 시간 값을 설정
		oneWeekAgo = LocalDateTime.now().minusWeeks(1);
	}

	@DisplayName("사용자 삭제 주기적 업데이트 테스트")
	@Test
	void deleteLongAccessUserTest() {
		// Given
		UserEntity inactiveUser1 = mock(UserEntity.class);
		UserEntity inactiveUser2 = mock(UserEntity.class);

		when(inactiveUser1.getUserId()).thenReturn(1L);
		when(inactiveUser2.getUserId()).thenReturn(2L);

		List<UserEntity> inactiveUsers = List.of(inactiveUser1, inactiveUser2);

		List<UserEntity> activeUsers = userRepository.findAllByLastAccessedAtBeforeAndIsUseTrue(oneWeekAgo);

		// When
		userScheduling.deleteLongAccessUser();

		// Then
		assertEquals(inactiveUsers.size(), 2);
		assertEquals(activeUsers.size(), 0);
	}
}
