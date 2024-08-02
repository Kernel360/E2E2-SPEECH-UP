package com.speech.up.script.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.script.entity.RecordEntity;
import com.speech.up.script.entity.ScriptEntity;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public class RecordAddDto {
    @Getter
    @ToString
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class RecordAddRequestDto{
        private final String audioPath;
        private final String languageCode;
        private final ScriptEntity script;

        public RecordAddRequestDto(String audioPath, String languageCode, ScriptEntity script) {
            this.audioPath = audioPath;
            this.languageCode = languageCode;
            this.script = script;
        }
    }

    @Getter
    @ToString
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class RecordAddResponseDto{
        private final Long scriptId;
        private final String audioPath;
        private final String languageCode;
        private final LocalDateTime createdAt;
        private final ScriptEntity script;

        public RecordAddResponseDto(RecordEntity recordEntity) {
            this.scriptId = recordEntity.getRecordId();
            this.audioPath = recordEntity.getAudioPath();
            this.languageCode = recordEntity.getLanguageCode();
            this.createdAt = recordEntity.getCreatedAt();
            this.script = recordEntity.getScript();
        }

        public static RecordAddResponseDto addRecord(RecordEntity recordEntity){
            return new RecordAddResponseDto(recordEntity);
        }
    }
}
