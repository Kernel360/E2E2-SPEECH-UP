package com.speech.up.user.service.dto;

import com.speech.up.user.entity.UserEntity;
import lombok.Getter;
import lombok.ToString;

public class UserGetInfoDto {
    @Getter
    @ToString
    public static class UserGetInfoResponseDto {
        private final String socialId;
        private final String name;
        private final String level;
        private final String authorization;

        public UserGetInfoResponseDto(UserEntity userEntity) {
            this.socialId = userEntity.getSocialId();
            this.name = userEntity.getName();
            this.level = userEntity.getLevel();
            this.authorization = userEntity.getAuthorization();
        }

        public static UserGetInfoResponseDto getUserInfo(UserEntity userEntity) {
            return new UserGetInfoResponseDto(userEntity);
        }
    }
}
