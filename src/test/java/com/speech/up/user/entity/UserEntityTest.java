package com.speech.up.user.entity;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class UserEntityTest {
    @ParameterizedTest
    @CsvSource(value = {
            "1001, 'test', 'zxcv@zxcv.com', 'email', 'bronze', 'authorization'"
    }, delimiter = ',')
    public void setEntityTest(Long userId, String username, String socialId, String token, String bronze, String authorization) {
        UserEntity userEntity = new UserEntity(userId, username, socialId, token, bronze, authorization);

        assertThat(userEntity.getUserId()).isEqualTo(1001L);
        assertThat(userEntity.getName()).isEqualTo("test");
        assertThat(userEntity.getSocialId()).isEqualTo("zxcv@zxcv.com");
        assertThat(userEntity.getEmail()).isEqualTo("email");
        assertThat(userEntity.getLevel()).isEqualTo("bronze");
        assertThat(userEntity.getAuthorization()).isEqualTo("authorization");
    }
}
