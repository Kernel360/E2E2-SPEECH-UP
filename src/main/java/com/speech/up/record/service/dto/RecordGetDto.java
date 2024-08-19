package com.speech.up.record.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.record.entity.RecordEntity;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public class RecordGetDto {
    @Getter
    @ToString
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Response {
        private final Long recordId;
        private final byte[] audioPath;
        private final String languageCode;
        private final LocalDateTime createdAt;

        public Response(RecordEntity recordEntity) {
            this.recordId = recordEntity.getRecordId();
            this.audioPath = recordEntity.getAudio();
            this.languageCode = recordEntity.getLanguageCode();
            this.createdAt = recordEntity.getCreatedAt();
        }

        public static Response getRecords(RecordEntity recordEntity) {
            return new Response(recordEntity);
        }
    }
}
