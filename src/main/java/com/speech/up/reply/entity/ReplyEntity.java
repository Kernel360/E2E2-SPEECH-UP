package com.speech.up.reply.entity;

import java.awt.*;
import java.time.LocalDateTime;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.board.entity.BoardEntity;
import com.speech.up.board.service.dto.BoardAddDto;
import com.speech.up.reply.service.dto.ReplyAddDto;
import com.speech.up.reply.service.dto.ReplyIsUseDto;
import com.speech.up.reply.service.dto.ReplyUpdateDto;
import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.script.service.dto.ScriptIsUseDto;
import com.speech.up.script.service.dto.ScriptUpdateDto;
import com.speech.up.user.entity.UserEntity;

import jakarta.persistence.Column;
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
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "reply")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReplyEntity extends ReplyBaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reply_id")
	private Long replyId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "social_id", referencedColumnName = "social_id",nullable = false)
	@JsonBackReference
	private UserEntity user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id", referencedColumnName = "board_id",nullable = false)
	@JsonBackReference
	private BoardEntity board;

	private String content;

	private Boolean isUse;

	private ReplyEntity(UserEntity userEntity, BoardEntity boardEntity, String content, Boolean isUse) {
		this.user = userEntity;
		this.board = boardEntity;
		this.content = content;
		this.isUse = isUse;

	}

	// 리플 생성
	private ReplyEntity(ReplyAddDto.Request replyAddRequestDto) {
		this(replyAddRequestDto.getUser(), replyAddRequestDto.getBoard(), replyAddRequestDto.getContent(), true);
	}

	// 리플 업데이트
	private ReplyEntity(ReplyUpdateDto.Request replyUpdateRequestDto) {
		this(replyUpdateRequestDto.getUser(), replyUpdateRequestDto.getBoard(),
			replyUpdateRequestDto.getContent(), true);
		this.replyId = replyUpdateRequestDto.getReplyId();
	}

	// 리플 삭제
	private ReplyEntity(ReplyIsUseDto.Request replyIsUseRequestDto
	) {
		this(replyIsUseRequestDto.getUser(), replyIsUseRequestDto.getBoard(),
			replyIsUseRequestDto.getContent(), false);
		this.replyId = replyIsUseRequestDto.getReplyId();
	}

	public static ReplyEntity create(ReplyAddDto.Request replyAddRequestDto) {
		return new ReplyEntity(replyAddRequestDto);
	}

	public static ReplyEntity update(ReplyUpdateDto.Request replyUpdateRequestDto) {
		return new ReplyEntity(replyUpdateRequestDto);
	}

	public static ReplyEntity delete(ReplyIsUseDto.Request replyIsUseRequestDto) {
		return new ReplyEntity(replyIsUseRequestDto);
	}

}
