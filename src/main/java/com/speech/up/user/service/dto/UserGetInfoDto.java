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
    public static class Request{
        private final Long userId;
        private final String socialId;
        private final String name;
        private final String level;
        private final String authorization;
        private final String providerType;
        private final String email;
        private final LocalDateTime lastAccessedAt;
        private final boolean isUse;

        private Request(UserEntity userEntity){
            this.userId = userEntity.getUserId();
            this.socialId = userEntity.getSocialId();
            this.name = userEntity.getName();
            this.providerType = userEntity.getProviderType();
            this.level = userEntity.getLevel();
            this.authorization = userEntity.getAuthorization();
            this.email = userEntity.getEmail();
            this.lastAccessedAt = userEntity.getLastAccessedAt();
            this.isUse = userEntity.isUse();
        }

    }

    @Getter
    @ToString
    public static class Response {
        private final Long userId;
        private final String socialId;
        private final String name;
        private final String level;
        private final String authorization;
        private final String email;
        private final LocalDateTime lastAccessedAt;
        private final boolean isUse;

        private Response(UserEntity userEntity) {
			this.userId = userEntity.getUserId();
			this.socialId = userEntity.getSocialId();
            this.name = userEntity.getName();
            this.level = userEntity.getLevel();
            this.authorization = userEntity.getAuthorization();
            this.email = userEntity.getEmail();
			this.lastAccessedAt = userEntity.getLastAccessedAt();
			this.isUse = userEntity.isUse();
		}

        public static Response getUserInfo(UserEntity userEntity) {
            return new Response(userEntity);
        }

        public static List<Response> getUsers(List<UserEntity> users) {
            return users.stream()
                .map(Response::getUserInfo)
                .collect(Collectors.toList());
        }


    }
}
