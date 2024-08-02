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
        private final String address;
        private final String rank;
        private final String authorization;

        public UserGetInfoResponseDto(UserEntity userEntity) {
            this.socialId = userEntity.getSocialId();
            this.name = userEntity.getName();
            this.address = userEntity.getAddress();
            this.rank = userEntity.getRank();
            this.authorization = userEntity.getAuthorization();
        }

        public static UserGetInfoResponseDto getUserInfo(UserEntity userEntity) {
            return new UserGetInfoResponseDto(userEntity);
        }
    }
}
