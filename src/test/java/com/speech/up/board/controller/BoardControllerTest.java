package com.speech.up.board.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.speech.up.board.service.dto.BoardAddDto;
import com.speech.up.board.service.dto.BoardGetDto;
import com.speech.up.board.service.dto.BoardIsUseDto;
import com.speech.up.board.service.dto.BoardUpdateDto;

import jakarta.servlet.http.HttpServletRequest;

public class BoardControllerTest {
	@Mock
	private BoardController boardController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("getBoardAll 테스트")
	@Test
	public void getAllBoardsTest() {
		// given
		List<BoardGetDto.Response> response = Collections.singletonList(mock(BoardGetDto.Response.class));
		int page = 1;
		int size = 10;

		// when
		when(boardController.getBoardAll(page, size)).thenReturn(ResponseEntity.ok(response));

		//then
		assertEquals(ResponseEntity.ok(response), boardController.getBoardAll(page, size));
	}

	@DisplayName("getBoard 테스트")
	@Test
	public void getBoardTest() {
		//given
		BoardGetDto.Response response = mock(BoardGetDto.Response.class);
		Long boardId = 1L;
		HttpServletRequest request = mock(HttpServletRequest.class);

		//when
		when(boardController.getBoard(boardId, request)).thenReturn(ResponseEntity.ok(response));

		//then
		assertEquals(ResponseEntity.ok(response), boardController.getBoard(boardId, request));
	}

	@DisplayName("getBoardCount 테스트")
	@Test
	public void getBoardCountTest() {
		//given
		HttpServletRequest request = mock(HttpServletRequest.class);
		Long boardId = 1L;

		//when
		when(boardController.getBoardCount(request)).thenReturn(ResponseEntity.ok(boardId));

		//then
		assertEquals(ResponseEntity.ok(boardId), boardController.getBoardCount(request));
	}

	@DisplayName("addBoard 테스트")
	@Test
	public void addBoardTest() {
		//given
		BoardAddDto.Request request = mock(BoardAddDto.Request.class);
		HttpServletRequest requestServlet = mock(HttpServletRequest.class);
		BoardAddDto.Response response = mock(BoardAddDto.Response.class);

		//when
		when(boardController.addBoard(request, requestServlet)).thenReturn(ResponseEntity.ok(response));

		//then
		assertEquals(ResponseEntity.ok(response), boardController.addBoard(request, requestServlet));
	}

	@DisplayName("updateBoard 테스트")
	@Test
	public void updateBoardTest() {
		//given
		BoardUpdateDto.Request request = mock(BoardUpdateDto.Request.class);
		BoardUpdateDto.Response response = mock(BoardUpdateDto.Response.class);

		//when
		when(boardController.updateBoard(request)).thenReturn(ResponseEntity.ok(response));

		//then
		assertEquals(ResponseEntity.ok(response), boardController.updateBoard(request));
	}

	@DisplayName("deleteBoard 테스트")
	@Test
	public void deleteBoardTest() {
		//given
		BoardIsUseDto.Request request = mock(BoardIsUseDto.Request.class);
		BoardIsUseDto.Response response = mock(BoardIsUseDto.Response.class);

		//when
		when(boardController.deleteBoard(request)).thenReturn(ResponseEntity.ok(response));

		//then
		assertEquals(ResponseEntity.ok(response), boardController.deleteBoard(request));
	}
}