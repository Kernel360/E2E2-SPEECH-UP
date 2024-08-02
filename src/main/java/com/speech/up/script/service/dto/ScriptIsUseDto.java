package com.speech.up.script.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.user.entity.UserEntity;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public class ScriptIsUseDto {

    @Getter
    @ToString
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class ScriptIsUseRequestDto{
        private final Long scriptId;
        private final String content;
        private final LocalDateTime createdAt;
        private final LocalDateTime modifiedAt;
        private final boolean isUse;
        private final UserEntity user;

        public ScriptIsUseRequestDto(Long scriptId, boolean isUse, String content, LocalDateTime createdAt, UserEntity user) {
            this.scriptId = scriptId;
            this.isUse = isUse;
            this.content = content;
            this.createdAt = createdAt;
            this.modifiedAt = LocalDateTime.now();
            this.user = user;
        }
    }

    @Getter
    @ToString
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class ScriptIsUseResponseDto{
        private final Long scriptId;
        private final boolean isUse;

        public ScriptIsUseResponseDto(ScriptEntity scriptEntity) {
            this.scriptId = scriptEntity.getScriptId();
            this.isUse = scriptEntity.isUse();
        }

        public static ScriptIsUseResponseDto deleteScript(ScriptEntity scriptEntity) {
            return new ScriptIsUseResponseDto(scriptEntity);
        }
    }
}
