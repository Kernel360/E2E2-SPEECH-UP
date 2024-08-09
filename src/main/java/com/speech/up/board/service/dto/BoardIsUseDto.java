package com.speech.up.board.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.board.entity.BoardEntity;
import com.speech.up.user.entity.UserEntity;

import lombok.Getter;
import lombok.ToString;

public class BoardIsUseDto {

	@Getter
	@ToString
	@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Request{
		private final Long boardId;
		private final String title;
		private final String content;
		private final boolean isUse;
		private final UserEntity user;

		public Request(Long boardId, String title, String content, boolean isUse, UserEntity user) {
			this.boardId = boardId;
			this.title = title;
			this.content = content;
			this.isUse = isUse;
			this.user = user;
		}
	}

	@Getter
	@ToString
	public static class Response{
		private final Long boardId;
		private final boolean isUse;

		public Response(BoardEntity boardEntity) {
			this.boardId = boardEntity.getBoardId();
			this.isUse = boardEntity.isUse();
		}
	}

	public static Response toResponse(BoardEntity boardEntity) {
		return new Response(boardEntity);
	}
}

