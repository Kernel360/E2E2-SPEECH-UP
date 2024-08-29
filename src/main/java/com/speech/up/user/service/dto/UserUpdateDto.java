package com.speech.up.user.service.dto;

import java.time.LocalDateTime;

import com.speech.up.common.enums.UserStatusCode;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class UserUpdateDto {

	@Getter
	@Builder
	@ToString
	public static class Request {
		private final Long userId;
		private final String socialId;
		private final String name;
		private final String level;
		private final String authorization;
		private final String providerType;
		private final String email;
		private final LocalDateTime lastAccessedAt;
		private boolean isUse;

		private Request(Long userId, String socialId, String name, String level, String authorization, String providerType, String email, LocalDateTime lastAccessedAt, boolean isUse) {
			this.userId = userId;
			this.socialId = socialId;
			this.name = name;
			this.level = level;
			this.authorization = authorization;
			this.providerType = providerType;
			this.email = email;
			this.lastAccessedAt = lastAccessedAt;
			this.isUse = isUse;
		}
	}
}
