package com.speech.up.user.service.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.speech.up.user.entity.UserEntity;
import lombok.Getter;
import lombok.ToString;

public class UserGetInfoDto {
    @Getter
    @ToString
    public static class UserGetInfoResponseDto {
        private final Long userId;
        private final String socialId;
        private final String name;
        private final String level;
        private final String authorization;
        private final String email;
        private final LocalDateTime lastAccessedAt;

        private UserGetInfoResponseDto(UserEntity userEntity) {
			this.userId = userEntity.getUserId();
			this.socialId = userEntity.getSocialId();
            this.name = userEntity.getName();
            this.level = userEntity.getLevel();
            this.authorization = userEntity.getAuthorization();
            this.email = userEntity.getEmail();
			this.lastAccessedAt = userEntity.getLastAccessedAt();
		}

        public static UserGetInfoResponseDto getUserInfo(UserEntity userEntity) {
            return new UserGetInfoResponseDto(userEntity);
        }

        public static List<UserGetInfoResponseDto> getUsers(List<UserEntity> users) {
            return users.stream()
                .map(UserGetInfoResponseDto::getUserInfo)
                .collect(Collectors.toList());
        }


    }
}
