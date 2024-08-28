package com.speech.up.reply.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.board.entity.BoardEntity;
import com.speech.up.reply.entity.ReplyEntity;
import com.speech.up.user.entity.UserEntity;

import lombok.Getter;
import lombok.ToString;

public class ReplyIsUseDto {
	@Getter
	@ToString
	@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Request {
		private final Long replyId;
		private final UserEntity user;
		private final BoardEntity board;
		private final String content;
		private final Boolean isUse;

		private Request(Long replyId, UserEntity user, BoardEntity board, String content, Boolean isUse) {
			this.replyId = replyId;
			this.user = user;
			this.board = board;
			this.content = content;
			this.isUse = isUse;
		}
	}

	@Getter
	@ToString
	@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Response {
		private final Long replyId;
		private final UserEntity userEntity;
		private final BoardEntity boardEntity;
		private final String content;
		private final Boolean isUse;

		private Response(ReplyEntity replyEntity) {
			this.replyId = replyEntity.getReplyId();
			this.userEntity = replyEntity.getUser();
			this.boardEntity = replyEntity.getBoard();
			this.content = replyEntity.getContent();
			this.isUse = replyEntity.getIsUse();
		}
	}

	public static Response toResponse(ReplyEntity replyEntity) {
		return new Response(replyEntity);
	}
}
