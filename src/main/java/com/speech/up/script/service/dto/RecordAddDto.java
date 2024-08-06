package com.speech.up.script.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.script.entity.RecordEntity;
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
        private final String audioPath;
        private final String languageCode;
        private final ScriptEntity script;

        public Request(MultipartFile file, String audioPath, String languageCode, ScriptEntity script) {
			this.file = file;
			this.audioPath = audioPath;
			this.languageCode = languageCode;
            this.script = script;
        }
    }

    @Getter
    @ToString
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Response {
        private final Long scriptId;
        private final String audioPath;
        private final String languageCode;
        private final LocalDateTime createdAt;
        private final ScriptEntity script;

        public Response(RecordEntity recordEntity) {
            this.scriptId = recordEntity.getRecordId();
            this.audioPath = recordEntity.getAudioPath();
            this.languageCode = recordEntity.getLanguageCode();
            this.createdAt = recordEntity.getCreatedAt();
            this.script = recordEntity.getScript();
        }
    }

    public static Response addRecord(RecordEntity recordEntity){
        return new Response(recordEntity);
    }
}
