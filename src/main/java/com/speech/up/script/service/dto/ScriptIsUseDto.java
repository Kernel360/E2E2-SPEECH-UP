package com.speech.up.script.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.user.entity.UserEntity;

import lombok.Getter;
import lombok.ToString;

public class ScriptIsUseDto {

	@Getter
	@ToString
	@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Request {
		private final Long scriptId;
		private final String content;
		private final boolean isUse;
		private final UserEntity user;

		public Request(Long scriptId, boolean isUse, String content, UserEntity user) {
			this.scriptId = scriptId;
			this.isUse = isUse;
			this.content = content;
			this.user = user;
		}
	}

	@Getter
	@ToString
	public static class Response {
		private final Long scriptId;
		private final boolean isUse;

		public Response(ScriptEntity scriptEntity) {
			this.scriptId = scriptEntity.getScriptId();
			this.isUse = scriptEntity.isUse();
		}
	}

	public static Response toResponse(ScriptEntity scriptEntity) {
		return new Response(scriptEntity);
	}
}
