package com.speech.up.script.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.script.entity.RecordEntity;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public class RecordGetDto {
    @Getter
    @ToString
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class RecordGetResponseDto {
        private final Long recordId;
        private final String audioPath;
        private final String languageCode;
        private final LocalDateTime createdAt;

        public RecordGetResponseDto(RecordEntity recordEntity) {
            this.recordId = recordEntity.getRecordId();
            this.audioPath = recordEntity.getAudioPath();
            this.languageCode = recordEntity.getLanguageCode();
            this.createdAt = recordEntity.getCreatedAt();
        }

        public static RecordGetResponseDto getRecords(RecordEntity recordEntity) {
            return new RecordGetResponseDto(recordEntity);
        }
    }
}
