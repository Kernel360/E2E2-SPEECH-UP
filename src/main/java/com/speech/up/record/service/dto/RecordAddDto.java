package com.speech.up.record.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.record.entity.RecordEntity;
import com.speech.up.script.entity.ScriptEntity;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

public class RecordAddDto {

    @Getter
    @ToString
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Request {
        private final MultipartFile file;
        private final String languageCode;
        private final ScriptEntity scriptEntity;

        public Request(MultipartFile file, String languageCode, ScriptEntity scriptEntity) {
            this.file = file;
            this.languageCode = languageCode;
            this.scriptEntity = scriptEntity;
        }
    }

    @Getter
    @ToString
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Response {
        private final Long recordId;
        private final byte[] audioPath;
        private final String languageCode;
        private final LocalDateTime createdAt;
        private final ScriptEntity scriptId; // scriptId로 변경

        public Response(RecordEntity recordEntity) {
            this.recordId = recordEntity.getRecordId();
            this.audioPath = recordEntity.getAudio();
            this.languageCode = recordEntity.getLanguageCode();
            this.createdAt = recordEntity.getCreatedAt();
            this.scriptId = recordEntity.getScript(); // scriptId로 변경
        }
    }

    public static Response toResponse(RecordEntity recordEntity) {
        return new Response(recordEntity);
    }
}