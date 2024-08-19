package com.speech.up.script.service.dto;

import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.user.entity.UserEntity;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public class ScriptAddDto {
	@Getter
	@ToString
	public static class Request {
		private final String content;
		private final UserEntity user;
		private final String title;

		private Request(String content, UserEntity user, String title) {
			this.content = content;
			this.user = user;
			this.title = title;
		}
	}

	@Getter
	@ToString
	public static class Response {
		private final String content;
		private final LocalDateTime createdAt;
		private final String title;

		private Response(ScriptEntity scriptEntity) {
			this.title = scriptEntity.getTitle();
			this.content = scriptEntity.getContent();
			this.createdAt = scriptEntity.getCreatedAt();
		}
	}

	public static Response toResponse(ScriptEntity scriptEntity) {
		return new Response(scriptEntity);
	}
}
