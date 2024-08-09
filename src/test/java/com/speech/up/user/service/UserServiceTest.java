/*
package com.speech.up.user.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;
import com.speech.up.user.service.dto.UserGetInfoDto;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserService userService;

	@Test
	@DisplayName("모든 유저 리스트 반환")
	void 모든_유저(){;
		UserEntity userEntity = new UserEntity(
			"google_1",
			"z@z.z",
			"name",
			"authorization"
		);

		//when(userRepository.findAll()).thenReturn(userService.getUsers());

	}



}
*/
