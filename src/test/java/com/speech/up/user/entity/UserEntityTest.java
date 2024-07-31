package com.speech.up.user.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserEntityTest {
    @Test
    public void setEntityTest() {
        UserEntity userEntity = UserEntity.builder()
                .userId(1001L)
                .name("딱중")
                .socialId("zxcv@zxcv.com")
                .password("pw")
                .token("token")
                .address("address")
                .rank("bronze")
                .authorization("authorization")
                .build();

        assertThat(userEntity.getUserId()).isEqualTo(1001L);
        assertThat(userEntity.getName()).isEqualTo("딱중");
        assertThat(userEntity.getSocialId()).isEqualTo("zxcv@zxcv.com");
        assertThat(userEntity.getPassword()).isEqualTo("pw");
        assertThat(userEntity.getToken()).isEqualTo("token");
        assertThat(userEntity.getAddress()).isEqualTo("address");
        assertThat(userEntity.getRank()).isEqualTo("bronze");
        assertThat(userEntity.getAuthorization()).isEqualTo("authorization");
    }
}
