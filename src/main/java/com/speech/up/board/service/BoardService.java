package com.speech.up.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.speech.up.board.entity.BoardEntity;
import com.speech.up.board.repository.BoardRepository;
import com.speech.up.board.service.checkValue.CheckListForPagination;
import com.speech.up.board.service.checkValue.CheckParamForPagination;
import com.speech.up.board.service.dto.BoardAddDto;
import com.speech.up.board.service.dto.BoardGetDto;
import com.speech.up.board.service.dto.BoardIsUseDto;
import com.speech.up.board.service.dto.BoardUpdateDto;
import com.speech.up.common.exception.http.InternalServerErrorException;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	private final UserRepository userRepository;

	public List<BoardGetDto.Response> getAllBoardList(int page, int size) {
		//Check value of page and size!
		CheckParamForPagination checkedValue = CheckParamForPagination.checkSizeAndPage(page,size);
		Pageable pageable = PageRequest.of(checkedValue.getPage() - 1, checkedValue.getSize());

		//Check list is not empty!
		Page<BoardEntity> boardList = boardRepository.findAllByIsUseTrue(pageable); // 정확한 타입 명시
		CheckListForPagination checkedList = CheckListForPagination.checkListIsNotEmpty(boardList);

		return BoardGetDto.Response.of(checkedList.getBoardList()); // 변환 후 List 반환
	}

	public BoardAddDto.Response addBoard(BoardAddDto.Request boardRequest){
		return BoardAddDto.toResponse(boardRepository.save(BoardEntity.create(boardRequest)));
	}

	public BoardUpdateDto.Response updateBoard(BoardUpdateDto.Request boardRequest){
		UserEntity userEntity = userRepository.findBySocialId(boardRequest.getUser().getSocialId());
		if(userEntity == null){
			throw new InternalServerErrorException("Not found user");
		}
		BoardEntity boardEntity = BoardEntity.update(boardRequest);
		return BoardUpdateDto.toResponse(boardRepository.save(boardEntity));
	}

	public BoardIsUseDto.Response deleteBoard(BoardIsUseDto.Request boardRequest){
		BoardEntity boardEntity = BoardEntity.delete(boardRequest);
		return BoardIsUseDto.toResponse(boardRepository.save(boardEntity));
	}

}
