package com.speech.up.script.entity;

import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.user.entity.UserEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScriptEntityTest {
    @Test
    public void setEntityTest() {
        //given
		UserEntity user = new UserEntity();
		String content = "test";
		ScriptAddDto.ScriptAddRequestDto scriptAddRequestDto = new ScriptAddDto.ScriptAddRequestDto(content, user);
        ScriptEntity scriptEntity = new ScriptEntity(scriptAddRequestDto);

		//when & then
        assertThat(scriptEntity.getContent()).isEqualTo("test");
        assertThat(scriptEntity.getUser()).isEqualTo(user);
    }
}
