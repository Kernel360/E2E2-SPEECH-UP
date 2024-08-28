package com.speech.up.board.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.speech.up.board.service.BoardService;
import com.speech.up.board.service.dto.BoardAddDto;
import com.speech.up.board.service.dto.BoardGetDto;
import com.speech.up.board.service.dto.BoardIsUseDto;
import com.speech.up.board.service.dto.BoardUpdateDto;

import jakarta.servlet.http.HttpServletRequest;

public class BoardControllerTest {
	@Mock
	BoardService boardService;

	@InjectMocks
	private BoardController boardController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("getBoardAll 테스트")
	@Test
	public void getAllBoardsTest() {
		// given
		int page = 1;
		int size = 10;

		// when
		ResponseEntity<List<BoardGetDto.Response>> actualResponse = boardController.getBoardAll(page, size);

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("getBoard 테스트")
	@Test
	public void getBoardTest() {
		//given
		Long boardId = 1L;
		HttpServletRequest request = mock(HttpServletRequest.class);

		//when
		ResponseEntity<BoardGetDto.Response> actualResponse = boardController.getBoard(boardId, request);

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("getBoardCount 테스트")
	@Test
	public void getBoardCountTest() {
		//given
		HttpServletRequest request = mock(HttpServletRequest.class);
		Long boardId = 1L;

		//when
		ResponseEntity<Long> actualResponse = boardController.getBoardCount(request);

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("addBoard 테스트")
	@Test
	public void addBoardTest() {
		//given
		BoardAddDto.Request request = mock(BoardAddDto.Request.class);
		HttpServletRequest requestServlet = mock(HttpServletRequest.class);

		//when
		ResponseEntity<BoardAddDto.Response> actualResponse = boardController.addBoard(request, requestServlet);

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("updateBoard 테스트")
	@Test
	public void updateBoardTest() {
		//given
		BoardUpdateDto.Request request = mock(BoardUpdateDto.Request.class);

		//when
		ResponseEntity<BoardUpdateDto.Response> actualResponse = boardController.updateBoard(request);

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("deleteBoard 테스트")
	@Test
	public void deleteBoardTest() {
		//given
		BoardIsUseDto.Request request = mock(BoardIsUseDto.Request.class);

		//when
		ResponseEntity<BoardIsUseDto.Response> actualResponse = boardController.deleteBoard(request);

		//then
		assertNotNull(actualResponse);
	}
}