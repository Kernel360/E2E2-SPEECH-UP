package com.speech.up.board.service.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.speech.up.board.entity.BoardEntity;

import lombok.Getter;
import lombok.ToString;

public class BoardGetDto {

	@Getter
	@ToString
	public static class Response {
		private final Long boardId;
		private final String title;
		private final String content;
		private final LocalDateTime createdAt;
		private final LocalDateTime modifiedAt;
		private final boolean isOwner;

		Response(BoardEntity boardEntity, boolean isOwner) {
			this.boardId = boardEntity.getBoardId();
			this.title = boardEntity.getTitle();
			this.content = boardEntity.getContent();
			this.createdAt = boardEntity.getCreatedAt();
			this.modifiedAt = boardEntity.getModifiedAt();
			this.isOwner = isOwner;
		}

		public static Response toResponse(BoardEntity boardEntity) {
			return new Response(boardEntity, false);
		}

		public static Response toResponseIsOwner(BoardEntity boardEntity) {
			return new Response(boardEntity, true);
		}

		public static List<Response> of(Page<BoardEntity> boardEntities) {
			return boardEntities
				.stream()
				.map(Response::toResponse)
				.collect(Collectors.toList());
		}
	}
}

