package com.speech.up.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.speech.up.board.entity.BoardEntity;
import com.speech.up.board.repository.BoardRepository;
import com.speech.up.board.service.dto.BoardAddDto;
import com.speech.up.board.service.dto.BoardGetDto;
import com.speech.up.board.service.dto.BoardIsUseDto;
import com.speech.up.board.service.dto.BoardUpdateDto;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	private final UserRepository userRepository;

	public List<BoardGetDto.Response> getAllBoardList(){
		List<BoardEntity> boardList = boardRepository.findAllByIsUseTrue();
		return BoardGetDto.Response.of(boardList);
	}

	public BoardGetDto.Response getBoardById(Long id){
		return BoardGetDto.Response.toResponse(boardRepository.findByBoardIdAndIsUseTrue(id));
	}

	public BoardAddDto.Response addBoard(BoardAddDto.Request boardRequest){
		return BoardAddDto.toResponse(boardRepository.save(BoardEntity.create(boardRequest)));
	}

	public BoardUpdateDto.Response updateBoard(BoardUpdateDto.Request boardRequest){
		UserEntity userEntity = userRepository.findBySocialId(boardRequest.getUser().getSocialId());
		if(userEntity == null){
			throw new IllegalArgumentException("유저 정보를 찾을 수 없습니다.");
		}
		BoardEntity boardEntity = BoardEntity.update(boardRequest);
		return BoardUpdateDto.toResponse(boardRepository.save(boardEntity));
	}

	public BoardIsUseDto.Response deleteBoard(BoardIsUseDto.Request boardRequest){
		BoardEntity boardEntity = BoardEntity.delete(boardRequest);
		return BoardIsUseDto.toResponse(boardRepository.save(boardEntity));
	}

}
