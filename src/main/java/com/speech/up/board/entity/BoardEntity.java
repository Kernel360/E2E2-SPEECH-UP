package com.speech.up.board.entity;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.board.service.dto.BoardAddDto;
import com.speech.up.board.service.dto.BoardIsUseDto;
import com.speech.up.board.service.dto.BoardUpdateDto;
import com.speech.up.user.entity.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicUpdate
@Table(name = "board")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BoardEntity extends BaseBoardEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "social_id", referencedColumnName = "social_id")
	@JsonBackReference
	private UserEntity user;

	private String title;

	private String content;

	private boolean isUse;

	private BoardEntity(UserEntity user, String title, boolean isUse) {
		this.user = user;
		this.isUse = isUse;
		this.title = title;
	}

	private BoardEntity(BoardAddDto.Request boardAddRequest){
		this(boardAddRequest.getUser(), boardAddRequest.getTitle(),true);
		this.title = boardAddRequest.getTitle();
		this.content = boardAddRequest.getContent();
	}

	public BoardEntity(BoardUpdateDto.Request boardAddRequest) {
		this(boardAddRequest.getUser(), boardAddRequest.getTitle(),true);
		this.boardId = boardAddRequest.getBoardId();
		this.title = boardAddRequest.getTitle();
		this.content = boardAddRequest.getContent();
	}

	public BoardEntity(BoardIsUseDto.Request boardIsUseRequest) {
		this(boardIsUseRequest.getUser(), boardIsUseRequest.getTitle(),false);
		this.boardId = boardIsUseRequest.getBoardId();
		this.title = boardIsUseRequest.getTitle();
		this.content = boardIsUseRequest.getContent();
	}

	public static BoardEntity create(BoardAddDto.Request boardAddRequest){
		return new BoardEntity(boardAddRequest);
	}

	public static BoardEntity update(BoardUpdateDto.Request boardAddRequest){
		return new BoardEntity(boardAddRequest);
	}

	public static BoardEntity delete(BoardIsUseDto.Request boardIsUseRequest){
		return new BoardEntity(boardIsUseRequest);
	}
}
