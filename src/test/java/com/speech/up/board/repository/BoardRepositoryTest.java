package com.speech.up.board.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.speech.up.board.entity.BoardEntity;

public class BoardRepositoryTest {

	@Mock
	BoardRepository boardRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("모든 게시판 정보 가져오기")
	@Test
	public void getAllBoardsTest() {
		//given
		Pageable pageable = mock(Pageable.class);

		//when
		Page<BoardEntity> boardList = boardRepository.findAllByIsUseTrueOrderByCreatedAtDesc(pageable);
		when(boardRepository.findAllByIsUseTrueOrderByCreatedAtDesc(pageable)).thenReturn(boardList);

		//then
		assertEquals(boardList, boardRepository.findAllByIsUseTrueOrderByCreatedAtDesc(pageable));
	}

	@DisplayName("사용 중인 게시판 갯수 가져오기")
	@Test
	public void getAllBoardsCountIsUse() {
		//given
		Long count = 0L;

		//when
		when(boardRepository.countByIsUseTrue()).thenReturn(count);

		//then
		assertEquals(count, boardRepository.countByIsUseTrue());
	}

	@DisplayName("보드 id로 게시판 찾기")
	@Test
	public void getBoardByIdTest() {
		//given
		BoardEntity boardEntity = mock(BoardEntity.class);
		Long boardId = 0L;

		//when
		when(boardRepository.findByBoardIdAndIsUseTrue(boardId)).thenReturn(boardEntity);

		//then
		assertEquals(boardRepository.findByBoardIdAndIsUseTrue(boardId), boardEntity);
	}

	@DisplayName("유저아이디로 사용 중인 게시판 갯수찾기")
	@Test
	public void getBoardByIdCountIsUse() {
		//given
		Long userId = 0L;
		Long count = 0L;

		//when
		when(boardRepository.countByUserUserIdAndIsUseTrue(userId)).thenReturn(count);

		//then
		assertEquals(count, boardRepository.countByUserUserIdAndIsUseTrue(userId));
	}
}
