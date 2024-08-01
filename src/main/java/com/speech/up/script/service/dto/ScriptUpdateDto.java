package com.speech.up.script.service.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.user.entity.UserEntity;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public class ScriptUpdateDto {

    @Getter
    @ToString
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class ScriptUpdateRequestDto{
        private final Long scriptId;
        private final String content;
        private final LocalDateTime modifiedAt;
        private final LocalDateTime createdAt;
        private final UserEntity user;


        public ScriptUpdateRequestDto(Long scriptId, String content, LocalDateTime createdAt,  UserEntity user) {
            this.scriptId = scriptId;
            this.content = content;
            this.modifiedAt = LocalDateTime.now();
            this.createdAt = createdAt;
            this.user = user;
        }
    }

    @Getter
    @ToString
    public static class ScriptUpdateResponseDto{
        private final String content;
        private final LocalDateTime modifiedAt;


        public ScriptUpdateResponseDto(ScriptEntity scriptEntity) {
            this.content = scriptEntity.getContent();
            this.modifiedAt = scriptEntity.getModifiedAt();
        }

        public static ScriptUpdateResponseDto updateScript(ScriptEntity scriptEntity) {
            return new ScriptUpdateResponseDto(scriptEntity);
        }

    }


}
