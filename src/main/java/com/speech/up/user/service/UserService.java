package com.speech.up.user.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.speech.up.auth.provider.JwtProvider;
import com.speech.up.common.enums.UserStatusCode;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;
import com.speech.up.user.service.dto.UserGetInfoDto;
import com.speech.up.user.service.dto.UserUpdateDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;

	public List<UserGetInfoDto.Response> getAllUsers() {
		return UserGetInfoDto.Response.getUsers(userRepository.findAll());
	}

	public UserGetInfoDto.Response getUserInfo(HttpServletRequest request) {
		String socialId = jwtProvider.getHeader(request);
		UserEntity userEntity = userRepository.findBySocialId(socialId)
			.orElseThrow(() -> new NoSuchElementException("not found UserEntity by socialId: " + socialId));

		return UserGetInfoDto.Response.getUserInfo(userEntity);
	}

	public void deleteUser(HttpServletRequest request) {
		String socialId = jwtProvider.getHeader(request);

		userRepository.deleteBySocialId(socialId);
	}

	public void unUsedUser(Long userId) {
		userHandler(userId, UserStatusCode.ROLE_BAN_USER);
	}

	public void restoreUser(Long userId) {
		userHandler(userId, UserStatusCode.ROLE_GENERAL_USER);
	}
	private void userHandler(Long userId, UserStatusCode userStatusCode){
		UserEntity user = userRepository.findById(userId)
			.orElseThrow(() -> new NoSuchElementException("not found UserEntity by socialId: " + userId));
		UserUpdateDto.Request userUpdateDto = UserUpdateDto.Request
			.builder()
			.userId(user.getUserId())
			.socialId(user.getSocialId())
			.name(user.getName())
			.level(user.getLevel())
			.email(user.getEmail())
			.authorization(userStatusCode.name())
			.providerType(user.getProviderType())
			.lastAccessedAt(user.getLastAccessedAt())
			.isUse(!user.isUse())
			.build();
		UserEntity updatedUser = UserEntity.update(userUpdateDto);
		userRepository.save(updatedUser);
	}
}
