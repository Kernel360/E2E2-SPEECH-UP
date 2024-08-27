package com.speech.up.reply.service.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.board.entity.BoardEntity;
import com.speech.up.reply.entity.ReplyEntity;
import com.speech.up.user.entity.UserEntity;

import lombok.Getter;
import lombok.ToString;

public class ReplyUpdateDto {
	@Getter
	@ToString
	@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Request {
		private final Long replyId;
		private final UserEntity user;
		private final BoardEntity board;
		private final String content;

		private Request(Long replyId, UserEntity userEntity, BoardEntity boardEntity, String content) {
			this.replyId = replyId;
			this.user = userEntity;
			this.board = boardEntity;
			this.content = content;
		}
	}

	@Getter
	@ToString
	@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Response {
		private final Long replyId;
		private final UserEntity user;
		private final BoardEntity board;
		private final String content;
		private final LocalDateTime modifiedAt;

		private Response(ReplyEntity replyEntity) {
			this.replyId = replyEntity.getReplyId();
			this.user = replyEntity.getUser();
			this.board = replyEntity.getBoard();
			this.content = replyEntity.getContent();
			this.modifiedAt = LocalDateTime.now();
		}
	}

	public static Response toResponse(ReplyEntity replyEntity) {
		return new Response(replyEntity);
	}
}
