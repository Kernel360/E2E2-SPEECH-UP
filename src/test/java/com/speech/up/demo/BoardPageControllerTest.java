package com.speech.up.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.speech.up.board.service.BoardService;
import com.speech.up.board.service.dto.BoardGetDto;

import jakarta.servlet.http.HttpServletRequest;

class BoardPageControllerTest {
	@Mock
	BoardService boardService;

	@Mock
	Model model;

	@InjectMocks
	BoardPageController boardPageController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("게시판 목록 페이지를 반환합니다.")
	@Test
	void boardsTest(){
		// given
		int page = 1;
		int size = 10;
		model = mock(Model.class);
		List<BoardGetDto.Response> expected = Collections.singletonList(mock(BoardGetDto.Response.class));

		// when
		when(boardService.getAllBoardList(page, size)).thenReturn(expected);
		String actualResponse = boardPageController.boards(page, size, model);

		// then
		assertNotNull(actualResponse);
	}

	@DisplayName("게시판 작성 페이지를 반환합니다.")
	@Test
	void boardsWrite() {
		String actualResponse = boardPageController.boardsWrite();

		assertNotNull(actualResponse);
		assertEquals("board-write", actualResponse);
	}

	@DisplayName("특정 게시판의 상세 정보를 조회하여 상세 페이지를 반환합니다.")
	@Test
	void boardsDetailTest() {
		// given
		Long boardId = 1L;
		model = mock(Model.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		BoardGetDto.Response expected = mock(BoardGetDto.Response.class);
		// when
		when(boardService.getBoardById(boardId, request)).thenReturn(expected);
		String actualResponse = boardPageController.boardsDetail(boardId, model, request);

		// then
		assertEquals("board-detail", actualResponse);
		assertNotNull(actualResponse);
	}

	@DisplayName("특정 게시판을 수정하기 위한 페이지를 반환합니다.")
	@Test
	void boardModifyTest() {
		// given
		Long boardId = 1L;
		Model model = mock(Model.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		BoardGetDto.Response expected = mock(BoardGetDto.Response.class);

		// when
		when(boardService.getBoardById(boardId, request)).thenReturn(expected);
		String actualResponse = boardPageController.boardModify(boardId, model, request);

		// then
		assertNotNull(actualResponse);
	}
}