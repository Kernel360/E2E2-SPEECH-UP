package com.speech.up.user.service.dto;

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

        private UserGetInfoResponseDto(UserEntity userEntity) {
			this.userId = userEntity.getUserId();
			this.socialId = userEntity.getSocialId();
            this.name = userEntity.getName();
            this.level = userEntity.getLevel();
            this.authorization = userEntity.getAuthorization();
            this.email = userEntity.getEmail();
        }

        public static UserGetInfoResponseDto getUserInfo(UserEntity userEntity) {
            return new UserGetInfoResponseDto(userEntity);
        }
    }
}
