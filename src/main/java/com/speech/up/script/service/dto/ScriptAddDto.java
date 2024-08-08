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

		public Request(String content, UserEntity user) {
			this.content = content;
			this.user = user;
		}
	}

	@Getter
	@ToString
	public static class Response {
		private final String content;
		private final LocalDateTime createdAt;

		public Response(ScriptEntity scriptEntity) {
			this.content = scriptEntity.getContent();
			this.createdAt = scriptEntity.getCreatedAt();
		}
	}

	public static Request toEntity(String content, UserEntity user) {
		return new Request(content, user);
	}
	public static Response toResponse(ScriptEntity scriptEntity) {
		return new Response(scriptEntity);
	}
}
