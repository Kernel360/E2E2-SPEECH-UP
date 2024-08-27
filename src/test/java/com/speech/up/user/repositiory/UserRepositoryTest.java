package com.speech.up.user.repositiory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;

public class UserRepositoryTest {
	@Mock
	UserRepository userRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("소셜아이디로 유저 검색")
	@Test
	public void getUserBySocialIdTest() {
		//given
		String socialId = "socialId";
		UserEntity user = mock(UserEntity.class);

		//when
		when(userRepository.findBySocialId(socialId)).thenReturn(user);

		//then
		assertEquals(userRepository.findBySocialId(socialId), user);
	}

	@DisplayName("소셜아이디로 유저 데이터 존재여부 검색")
	@Test
	public void existsUserBySocialIdTest() {
		//given
		String socialId = "socialId";
		boolean exists = true;
		//when
		when(userRepository.existsBySocialId(socialId)).thenReturn(true);

		//then
		assertEquals(exists, userRepository.existsBySocialId(socialId));
	}

	@DisplayName("소셜아이디로 데이터 삭제")
	@Test
	public void deleteUserBySocialIdTest() {
		//given
		String socialId = "socialId";
		UserEntity user = mock(UserEntity.class);
		when(userRepository.findBySocialId(socialId)).thenReturn(user);

		//when
		userRepository.deleteBySocialId(socialId);
		when(userRepository.findBySocialId(socialId)).thenReturn(null);

		//then
		verify(userRepository, times(1)).deleteBySocialId(socialId);
		assertFalse(userRepository.existsBySocialId(socialId));
	}

	@DisplayName("유저아이디로 유저 검색")
	@Test
	public void getUserByUserIdTest() {
		//given
		Long userId = 1L;
		UserEntity user = mock(UserEntity.class);

		//when
		when(userRepository.findByUserId(userId)).thenReturn(user);

		//then
		assertEquals(userRepository.findByUserId(userId), user);
	}

	@DisplayName("마지막접속일이 7일 전인 유저 리스트 검색")
	@Test
	public void sevenDaysAgoLastAccessUserTest() {
		//given
		LocalDateTime oneWeekAgo = LocalDateTime.now().minusDays(7);
		List<UserEntity> user = Collections.singletonList(mock(UserEntity.class));

		//when
		when(userRepository.findAllByLastAccessedAtBeforeAndIsUseTrue(oneWeekAgo)).thenReturn(user);

		//then
		assertEquals(userRepository.findAllByLastAccessedAtBeforeAndIsUseTrue(oneWeekAgo), user);
	}

}
