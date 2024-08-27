package com.speech.up.board.service.dto;

import com.speech.up.board.entity.BoardEntity;
import com.speech.up.user.entity.UserEntity;

import lombok.Getter;
import lombok.ToString;

public class BoardAddDto {

	@Getter
	@ToString
	public static class Request {
		private final String title;
		private final String content;
		private final UserEntity user;

		public Request(String title, String content, UserEntity user) {
			this.title = title;
			this.content = content;
			this.user = user;
		}
	}

	@Getter
	@ToString
	public static class Response {
		private final String title;
		private final String content;

		public Response(BoardEntity boardEntity) {
			this.title = boardEntity.getTitle();
			this.content = boardEntity.getContent();
		}
	}

	public static Response toResponse(BoardEntity boardEntity) {
		return new Response(boardEntity);
	}
}
