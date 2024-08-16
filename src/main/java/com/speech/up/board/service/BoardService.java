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
import com.speech.up.common.exception.http.BadRequestException;
import com.speech.up.common.exception.http.InternalServerErrorException;
import com.speech.up.log.CustomLogger;
import com.speech.up.oAuth.provider.JwtProvider;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;
	private final CustomLogger customLogger;

	public List<BoardGetDto.Response> getAllBoardList(int page, int size) {
		//Check value of page and size!
		CheckParamForPagination checkedValue = CheckParamForPagination.checkSizeAndPage(page,size);
		Pageable pageable = PageRequest.of(checkedValue.getPage() - 1, checkedValue.getSize());

		//Check list is not empty!
		Page<BoardEntity> boardList = boardRepository.findAllByIsUseTrueOrderByCreatedAtDesc(pageable); // 정확한 타입 명시
		CheckListForPagination checkedList = CheckListForPagination.checkListIsNotEmpty(boardList);

		return BoardGetDto.Response.of(checkedList.getBoardList()); // 변환 후 List 반환
	}

	public BoardGetDto.Response getBoardById(Long id, HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if(token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		if(token == null || token.isEmpty()) {
			return BoardGetDto.Response.toResponse(boardRepository.findByBoardIdAndIsUseTrue(id));
		}
		String socialId = jwtProvider.validate(token);
		UserEntity userEntity = userRepository.findBySocialId(socialId);
		BoardEntity board = boardRepository.findByBoardIdAndIsUseTrue(id);
		if(userEntity.getUserId().equals(board.getUser().getUserId())){
			return BoardGetDto.Response.toResponseIsOwner(board);
		}
		return BoardGetDto.Response.toResponse(board);
	}

	public BoardAddDto.Response addBoard(BoardAddDto.Request boardRequest, HttpServletRequest request){
		String token = request.getHeader("Authorization");

		if(token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		String socialId = jwtProvider.validate(token);
		UserEntity userEntity = userRepository.findBySocialId(socialId);
		if(userEntity == null){
			throw new BadRequestException("로그인을 해주세요.");
		}

		return BoardAddDto.toResponse(boardRepository.save(BoardEntity.create(boardRequest)));
	}

	public BoardUpdateDto.Response updateBoard(BoardUpdateDto.Request boardRequest){
		UserEntity userEntity = userRepository.findByUserId(boardRequest.getUser().getUserId());
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

	public long getTotalBoardCount() {
		return boardRepository.countByIsUseTrue();
	}

}
