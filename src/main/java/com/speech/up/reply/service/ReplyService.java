package com.speech.up.reply.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.speech.up.board.entity.BoardEntity;
import com.speech.up.board.repository.BoardRepository;
import com.speech.up.board.service.dto.BoardGetDto;
import com.speech.up.common.exception.http.InternalServerErrorException;
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
		checkReply(
			replyUpdateRequestDto.getUser().getUserId(),
			replyUpdateRequestDto.getBoard().getBoardId(),
			replyUpdateRequestDto.getReplyId()
		);
		return ReplyUpdateDto.toResponse(replyRepository.save(ReplyEntity.update(replyUpdateRequestDto)));
	}

	public ReplyIsUseDto.Response isUseReply(ReplyIsUseDto.Request replyIsUseRequestDto) {
		checkReply(
			replyIsUseRequestDto.getUser().getUserId(),
			replyIsUseRequestDto.getBoard().getBoardId(),
			replyIsUseRequestDto.getReplyId()
		);

		return ReplyIsUseDto.toResponse(replyRepository.save(ReplyEntity.delete(replyIsUseRequestDto)));
	}

	public void checkReply(Long userId, Long boardId , Long replyId){
		checkUserEntity(userId);
		checkBoardEntity(boardId);
		checkReplyEntity(replyId);
	}

	void checkUserEntity(Long userId){
		if(!userRepository.existsById(userId)){
			throw new InternalServerErrorException("해당 유저가 존재하지 않습니다.");
		}
	}

	void checkBoardEntity(Long boardId){
		if(!boardRepository.existsById(boardId)){
			throw new InternalServerErrorException("해당 게시글이 존재하지 않습니다.");
		}
	}

	void checkReplyEntity(Long replyId){
		if(!replyRepository.existsById(replyId)){
			throw new InternalServerErrorException("해당 댓글이 존재하지 않습니다.");
		}
		if(!replyRepository.existsByReplyIdAndIsUseTrue(replyId)){
			throw new InternalServerErrorException("해당 댓글이 FALSE 처리 되어있습니다.");
		}

	}
}
