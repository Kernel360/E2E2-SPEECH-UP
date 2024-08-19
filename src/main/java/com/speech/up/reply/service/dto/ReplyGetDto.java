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
		private final UserEntity user;
		private final BoardEntity board;
		private final String content;


		public Response(ReplyEntity replyEntity) {
			this.replyId = replyEntity.getReplyId();
			this.user = replyEntity.getUser();
			this.board = replyEntity.getBoard();
			this.content = replyEntity.getContent();

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
