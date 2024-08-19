package com.speech.up.reply.service.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.board.entity.BoardEntity;
import com.speech.up.reply.entity.ReplyEntity;
import com.speech.up.user.entity.UserEntity;

import lombok.Getter;
import lombok.ToString;

public class ReplyAddDto {

	@Getter
	@ToString
	@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Request {
		private final UserEntity user;
		private final BoardEntity board;
		private final String content;

		public Request(UserEntity user, BoardEntity board, String content) {
			this.user = user;
			this.board = board;
			this.content = content;
		}

	}

	@Getter
	@ToString
	@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Response {
		private final UserEntity user;
		private final BoardEntity board;
		private final String content;
		private final LocalDateTime createdAt;

		public Response(ReplyEntity replyEntity) {
			this.user = replyEntity.getUser();
			this.board = replyEntity.getBoard();
			this.content = replyEntity.getContent();
			this.createdAt = replyEntity.getCreatedAt();

		}
	}

	public static Response toResponse(ReplyEntity replyEntity) {
		return new Response(replyEntity);
	}

}
