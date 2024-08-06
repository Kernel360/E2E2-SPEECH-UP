package com.speech.up.script.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.user.entity.UserEntity;

public class ScriptEntityTest {
    @Test
    public void setEntityTest() {

        //given
		UserEntity user = new UserEntity(  1001L, "test", "zxcv@zxcv.com", "pw", "token", "address", "bronze", "authorization");

        String content = "test";
		ScriptAddDto.Request scriptAddRequestDto = new ScriptAddDto.Request(content, user);
        ScriptEntity scriptEntity = ScriptEntity.create(scriptAddRequestDto);

		//when & then
        assertThat(scriptEntity.getContent()).isEqualTo("test");
        assertThat(scriptEntity.getUser()).isEqualTo(user);
    }
}
