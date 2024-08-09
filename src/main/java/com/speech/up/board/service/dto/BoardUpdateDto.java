package com.speech.up.board.service.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.board.entity.BoardEntity;
import com.speech.up.user.entity.UserEntity;

import lombok.Getter;
import lombok.ToString;

public class BoardUpdateDto {
	@Getter
	@ToString
	@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Request{
		private final Long boardId;
		private final String title;
		private final String content;
		private final UserEntity user;

		public Request(Long boardId, String title, String content, UserEntity user) {
			this.boardId = boardId;
			this.title = title;
			this.content = content;
			this.user = user;
		}
	}

	@Getter
	@ToString
	public static class Response{
		private final String title;
		private final String content;
		private final LocalDateTime modifiedAt;


		public Response(BoardEntity boardEntity) {
			this.title = boardEntity.getTitle();
			this.content = boardEntity.getContent();
			this.modifiedAt = boardEntity.getModifiedAt();
		}
	}

	public static Response toResponse(BoardEntity boardEntity) {
		return new Response(boardEntity);
	}
}
