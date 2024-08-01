package com.speech.up.script.service.dto;

import com.speech.up.script.entity.ScriptEntity;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public class ScriptGetDto {

    @Getter
    @ToString
    public static class ScriptGetRequestDto{
        private Long userId;


    }

    @Getter
    @ToString
    public static class ScriptGetResponseDto{
        private final Long scriptId;
        private final String content;
        private final LocalDateTime createdAt;
        private final LocalDateTime modifiedAt;

        ScriptGetResponseDto(ScriptEntity scriptEntity){
            this.scriptId = scriptEntity.getScriptId();
            this.content = scriptEntity.getContent();
            this.createdAt = scriptEntity.getCreatedAt();
            this.modifiedAt = scriptEntity.getModifiedAt();
        }

        public static ScriptGetResponseDto getScripts(ScriptEntity scriptEntity){
            return new ScriptGetResponseDto(scriptEntity);
        }
    }
}
