package com.speech.up.script.service.dto;

import com.speech.up.script.entity.ScriptEntity;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public class ScriptGetDto {

	@Getter
	@ToString
	public static class Response {
		private final Long scriptId;
		private final String content;
		private final LocalDateTime createdAt;
		private final LocalDateTime modifiedAt;

		Response(ScriptEntity scriptEntity) {
			this.scriptId = scriptEntity.getScriptId();
			this.content = scriptEntity.getContent();
			this.createdAt = scriptEntity.getCreatedAt();
			this.modifiedAt = scriptEntity.getModifiedAt();
		}

		public static Response toResponse(ScriptEntity scriptEntity) {
			return new Response(scriptEntity);
		}
	}
}
