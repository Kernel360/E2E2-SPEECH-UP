package com.speech.up.reply.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.speech.up.board.entity.BoardEntity;
import com.speech.up.board.repository.BoardRepository;
import com.speech.up.board.service.dto.BoardGetDto;
import com.speech.up.reply.entity.ReplyEntity;
import com.speech.up.reply.repository.ReplyRepository;
import com.speech.up.reply.service.dto.ReplyAddDto;
import com.speech.up.reply.service.dto.ReplyGetDto;
import com.speech.up.reply.service.dto.ReplyIsUseDto;
import com.speech.up.reply.service.dto.ReplyUpdateDto;
import com.speech.up.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {

	private final ReplyRepository replyRepository;
	private final UserRepository userRepository;
	private final BoardRepository boardRepository;

	public List<ReplyGetDto.Response> getAllReplyList() {
		List<ReplyEntity> replyList = replyRepository.findAll();
		return ReplyGetDto.Response.of(replyList);
	}

	public ReplyGetDto.Response getReply(Long replyId) {



		return ReplyGetDto.Response.totResponse(replyRepository.findByReplyIdAndIsUseTrue(replyId));
	}

	public ReplyAddDto.Response addReply(ReplyAddDto.Request replyAddRequestDto) {

		return ReplyAddDto.toResponse(replyRepository.save(ReplyEntity.create(replyAddRequestDto)));
	}

	public ReplyUpdateDto.Response updateReply(ReplyUpdateDto.Request replyUpdateRequestDto) {


		return ReplyUpdateDto.toResponse(replyRepository.save(ReplyEntity.update(replyUpdateRequestDto)));
	}

	public ReplyIsUseDto.Response isUseReply(ReplyIsUseDto.Request replyIsUseRequestDto) {

		return ReplyIsUseDto.toResponse(replyRepository.save(ReplyEntity.delete(replyIsUseRequestDto)));
	}
}
