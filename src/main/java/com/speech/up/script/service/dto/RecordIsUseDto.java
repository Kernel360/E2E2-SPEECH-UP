package com.speech.up.script.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.script.entity.RecordEntity;
import com.speech.up.script.entity.ScriptEntity;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public class RecordIsUseDto {
    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class RecordIsUseRequestDto{
        private final Long recordId;
        private final String audioPath;
        private final String languageCode;
        private final LocalDateTime createdAt;
        private final boolean isUse;
        private final ScriptEntity script;

        public RecordIsUseRequestDto(Long recordId, String audioPath, String languageCode, LocalDateTime createdAt, boolean isUse, ScriptEntity script) {
            this.recordId = recordId;
            this.audioPath = audioPath;
            this.languageCode = languageCode;
            this.createdAt = createdAt;
            this.isUse = isUse;
            this.script = script;
        }
    }

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class RecordIsUseResponseDto{
        private final Long recordId;
        private final boolean isUse;

        public RecordIsUseResponseDto(RecordEntity recordEntity) {
            this.recordId = recordEntity.getRecordId();
            this.isUse = recordEntity.isUse();
        }

        public static RecordIsUseResponseDto deleteRecord(RecordEntity recordEntity) {
            return new RecordIsUseResponseDto(recordEntity);
        }
    }
}
