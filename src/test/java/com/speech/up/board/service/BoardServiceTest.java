package com.speech.up.board.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.speech.up.board.service.dto.BoardAddDto;
import com.speech.up.board.service.dto.BoardGetDto;
import com.speech.up.board.service.dto.BoardIsUseDto;
import com.speech.up.board.service.dto.BoardUpdateDto;
import com.speech.up.common.exception.http.BadRequestException;

import jakarta.servlet.http.HttpServletRequest;

public class BoardServiceTest {

	@Mock
	BoardService boardService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("BoardList 다건 조회")
	@Test
	void getAllBoardList() {
		//given
		List<BoardGetDto.Response> response = Collections.singletonList(mock(BoardGetDto.Response.class));
		int page = 1;
		int size = 10;

		//when
		when(boardService.getAllBoardList(page, size)).thenReturn(response);
		when(boardService.getAllBoardList(-1, size)).thenThrow(
			new BadRequestException("page or size can not be less than zero"));
		when(boardService.getAllBoardList(page, 101)).thenThrow(
			new BadRequestException("size is must be less than 100."));

		//then
		assertEquals(response, boardService.getAllBoardList(page, size));
		assertThrows(BadRequestException.class, () -> boardService.getAllBoardList(-1, size));
		assertThrows(BadRequestException.class, () -> boardService.getAllBoardList(page, 101));
	}

	@DisplayName("BoardList 단건 조회")
	@Test
	void getBoardById() {
		// given
		HttpServletRequest request = mock(HttpServletRequest.class);
		BoardGetDto.Response response = mock(BoardGetDto.Response.class);
		Long boardId = 1L;

		// when
		when(boardService.getBoardById(boardId, request)).thenReturn(response);

		// then
		assertEquals(response, boardService.getBoardById(boardId, request));
	}

	@DisplayName("게시판 생성 테스트")
	@Test
	void addBoard() {
		// given
		BoardAddDto.Request request = mock(BoardAddDto.Request.class);
		BoardAddDto.Response response = mock(BoardAddDto.Response.class);
		HttpServletRequest servletRequest = mock(HttpServletRequest.class);

		// when
		when(boardService.addBoard(request, servletRequest)).thenReturn(response);

		// then
		assertEquals(response, boardService.addBoard(request, servletRequest));
	}

	@DisplayName("게시판 업데이트 테스트")
	@Test
	void updateBoard() {
		// given
		BoardUpdateDto.Request request = mock(BoardUpdateDto.Request.class);
		BoardUpdateDto.Response response = mock(BoardUpdateDto.Response.class);

		// when
		when(boardService.updateBoard(request)).thenReturn(response);

		// then
		assertEquals(response, boardService.updateBoard(request));
	}

	@DisplayName("보드 삭제 테스트")
	@Test
	void deleteBoard() {
		// given
		BoardIsUseDto.Request request = mock(BoardIsUseDto.Request.class);
		BoardIsUseDto.Response response = mock(BoardIsUseDto.Response.class);

		// when
		when(boardService.deleteBoard(request)).thenReturn(response);

		// then
		assertEquals(response, boardService.deleteBoard(request));
	}

	@DisplayName("게시판 총 갯수 조회")
	@Test
	void getTotalBoardCount() {
		// given
		Long boardCount = 132131L;

		// when
		when(boardService.getTotalBoardCount()).thenReturn(boardCount);

		// then
		assertEquals(boardCount, boardService.getTotalBoardCount());
	}

	@DisplayName("유저의 게시글 갯수 조회")
	@Test
	void getBoardCount() {
		// given
		HttpServletRequest request = mock(HttpServletRequest.class);
		Long boardCount = 132131L;

		// when
		when(boardService.getBoardCount(request)).thenReturn(boardCount);

		// then
		assertEquals(boardCount, boardService.getBoardCount(request));
	}

}