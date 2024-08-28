package com.speech.up.board.service;

import java.util.List;
import java.util.NoSuchElementException;

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
import com.speech.up.auth.provider.JwtProvider;
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

	public List<BoardGetDto.Response> getAllBoardList(int page, int size) {
		CheckParamForPagination checkedValue = CheckParamForPagination.checkSizeAndPage(page, size);
		Pageable pageable = PageRequest.of(checkedValue.getPage() - 1, checkedValue.getSize());

		Page<BoardEntity> boardList = boardRepository.findAllByIsUseTrueOrderByCreatedAtDesc(pageable);
		CheckListForPagination checkedList = CheckListForPagination.checkListIsNotEmpty(boardList);

		return BoardGetDto.Response.of(checkedList.getBoardList());
	}

	public BoardGetDto.Response getBoardById(Long id, HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		BoardEntity board = boardRepository.findByBoardIdAndIsUseTrue(id);

		if (isTokenInvalid(token)) {
			return BoardGetDto.Response.toResponse(board);
		}

		String socialId = jwtProvider.getHeader(request);
		UserEntity userEntity = userRepository.findBySocialId(socialId)
			.orElseThrow(() -> new NoSuchElementException("User not found by socialId: " + socialId));

		boolean isOwner = userEntity.getUserId().equals(board.getUser().getUserId());
		return isOwner ? BoardGetDto.Response.toResponseIsOwner(board) : BoardGetDto.Response.toResponse(board);
	}

	private boolean isTokenInvalid(String token) {
		return token == null || token.isEmpty();
	}

	public BoardAddDto.Response addBoard(BoardAddDto.Request boardRequest, HttpServletRequest request) {
		String socialId = jwtProvider.getHeader(request);

		userRepository.findBySocialId(socialId)
			.orElseThrow(() -> new NoSuchElementException("not found UserEntity by socialId : " + socialId));

		return BoardAddDto.toResponse(boardRepository.save(BoardEntity.create(boardRequest)));
	}

	public BoardUpdateDto.Response updateBoard(BoardUpdateDto.Request boardRequest) {
		UserEntity userEntity = userRepository.findByUserId(boardRequest.getUser().getUserId())
			.orElseThrow(() -> new NoSuchElementException(
				"User not found by userId: " + boardRequest.getUser().getUserId()
			));

		if (userEntity == null) {
			throw new InternalServerErrorException("Not found user");
		}

		BoardEntity boardEntity = BoardEntity.update(boardRequest);

		return BoardUpdateDto.toResponse(boardRepository.save(boardEntity));
	}

	public BoardIsUseDto.Response deleteBoard(BoardIsUseDto.Request boardRequest) {
		BoardEntity boardEntity = BoardEntity.delete(boardRequest);

		return BoardIsUseDto.toResponse(boardRepository.save(boardEntity));
	}

	public Long getTotalBoardCount() {
		return boardRepository.countByIsUseTrue();
	}

	public Long getBoardCount(HttpServletRequest request) {
		String socialId = jwtProvider.getHeader(request);
		UserEntity userEntity = userRepository.findBySocialId(socialId)
			.orElseThrow(() -> new NoSuchElementException("not found UserEntity by socialId : " + socialId));

		return boardRepository.countByUserUserIdAndIsUseTrue(userEntity.getUserId());
	}
}
