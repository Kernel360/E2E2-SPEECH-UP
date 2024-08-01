package com.speech.up.script.service.dto;

import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.user.entity.UserEntity;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public class ScriptAddDto {
    @Getter
    @ToString
    public static class ScriptAddRequestDto{
        private final String content;
        private final UserEntity user;

        public ScriptAddRequestDto(String content, UserEntity user) {
            this.content = content;
            this.user = user;
        }
    }

    @Getter
    @ToString
    public static class ScriptAddResponseDto{
        private final String content;
        private final LocalDateTime createdAt;

        public ScriptAddResponseDto(ScriptEntity scriptEntity) {
            this.content = scriptEntity.getContent();
            this.createdAt = scriptEntity.getCreatedAt();
        }

        public static ScriptAddResponseDto addScript(ScriptEntity scriptEntity) {
            return new ScriptAddResponseDto(scriptEntity);
        }

    }
}
