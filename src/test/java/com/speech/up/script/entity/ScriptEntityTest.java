package com.speech.up.script.entity;

import com.speech.up.user.entity.UserEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScriptEntityTest {
    @Test
    public void setEntityTest() {
        UserEntity user = new UserEntity();

        ScriptEntity scriptEntity = ScriptEntity.builder()
                .content("test")
                .user(user)
                .build();

        assertThat(scriptEntity.getContent()).isEqualTo("test");
        assertThat(scriptEntity.getUser()).isEqualTo(user);
    }
}
