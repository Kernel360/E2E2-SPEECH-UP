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
	public static class Request {
		private final Long scriptId;
		private final String content;
		private final String title;
		private final UserEntity user;
		private final boolean isUse;

		private Request(Long scriptId, String content, String title, UserEntity user, boolean isUse) {
			this.scriptId = scriptId;
			this.content = content;
			this.title = title;
			this.isUse = isUse;
			this.user = user;
		}
	}

	@Getter
	@ToString
	public static class Response {
		private final String title;
		private final String content;
		private final LocalDateTime createdAt;
		private final LocalDateTime modifiedAt;

		private Response(ScriptEntity scriptEntity) {
			this.title = scriptEntity.getTitle();
			this.content = scriptEntity.getContent();
			this.createdAt = scriptEntity.getCreatedAt();
			this.modifiedAt = scriptEntity.getModifiedAt();
		}
	}

	public static Response toResponse(ScriptEntity scriptEntity) {
		return new Response(scriptEntity);
	}
}
