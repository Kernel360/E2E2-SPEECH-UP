package com.speech.up.reply.service.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.board.entity.BoardEntity;
import com.speech.up.reply.entity.ReplyEntity;
import com.speech.up.user.entity.UserEntity;

import lombok.Getter;
import lombok.ToString;

public class ReplyGetDto {

	@Getter
	@ToString
	@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Response {
		private final Long replyId;
		private final Long userId;
		private final Long boardId;
		private final String content;
		private final String name;
		private final LocalDateTime createdAt;
		private final LocalDateTime modifiedAt;


		private Response(ReplyEntity replyEntity) {
			this.replyId = replyEntity.getReplyId();
			this.userId = replyEntity.getUser().getUserId();
			this.boardId = replyEntity.getBoard().getBoardId();
			this.content = replyEntity.getContent();
			this.name = replyEntity.getName();
			this.createdAt = replyEntity.getCreatedAt();
			this.modifiedAt = replyEntity.getModifiedAt();
		}

		public static List<ReplyGetDto.Response> of(List<ReplyEntity> replyEntities) {
			return replyEntities
				.stream()
				.map(ReplyGetDto.Response::totResponse)
				.collect(Collectors.toList());
		}

		public static Response totResponse(ReplyEntity replyEntity) {
			return new Response(replyEntity);
		}

	}
}
