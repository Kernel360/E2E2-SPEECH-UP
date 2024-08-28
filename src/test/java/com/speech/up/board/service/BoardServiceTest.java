package com.speech.up.board.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.speech.up.auth.provider.JwtProvider;
import com.speech.up.board.entity.BoardEntity;
import com.speech.up.board.repository.BoardRepository;
import com.speech.up.board.service.dto.BoardAddDto;
import com.speech.up.board.service.dto.BoardGetDto;
import com.speech.up.board.service.dto.BoardIsUseDto;
import com.speech.up.board.service.dto.BoardUpdateDto;
import com.speech.up.common.exception.http.BadRequestException;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

public class BoardServiceTest {

	@Mock
	BoardRepository boardRepository;
	@Mock
	UserRepository userRepository;
	@Mock
	JwtProvider jwtProvider;
	@Mock
	UserEntity userEntity;
	@Mock
	BoardEntity boardEntity;

	@InjectMocks
	BoardService boardService;

	Long boardId;
	Long userId;
	Long boardCount;
	String socialId;
	String title;
	Long scriptId;
	String content;
	LocalDateTime modifiedAt;
	LocalDateTime createdAt;
	boolean isUse;
	int page;
	int size;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		socialId = "mockSocialId";
		boardId = 1L;
		userId = 1L;
		modifiedAt = LocalDateTime.now();
		scriptId = 1L;
		title = "title";
		content = "content";
		isUse = false;
		page = 1;
		size = 10;
		boardCount = 1L;

		userEntity = mock(UserEntity.class);
		boardEntity = mock(BoardEntity.class);
		when(userEntity.getUserId()).thenReturn(userId);
		when(userEntity.getSocialId()).thenReturn(socialId);
		when(boardEntity.getTitle()).thenReturn(title);
		when(boardEntity.getContent()).thenReturn(content);
		when(boardEntity.getModifiedAt()).thenReturn(modifiedAt);
		when(boardEntity.getCreatedAt()).thenReturn(createdAt);
		when(boardEntity.getBoardId()).thenReturn(boardId);
		when(boardEntity.getUser()).thenReturn(userEntity);
	}

	@DisplayName("BoardList 다건 조회")
	@Test
	void getAllBoardList() {
		//given
		Pageable pageable = PageRequest.of(0, 10);
		List<BoardEntity> boardEntities = Arrays.asList(boardEntity, boardEntity);
		Page<BoardEntity> boardEntityPage = new PageImpl<>(boardEntities, pageable, boardEntities.size());

		when(boardRepository.findAllByIsUseTrueOrderByCreatedAtDesc(pageable)).thenReturn(boardEntityPage);
		List<BoardGetDto.Response> actualResponse = boardService.getAllBoardList(page, size);

		//then
		assertNotNull(actualResponse);
		assertThrows(BadRequestException.class, () -> boardService.getAllBoardList(-1, size));
		assertThrows(BadRequestException.class, () -> boardService.getAllBoardList(page, 101));
	}

	@DisplayName("BoardList 단건 조회")
	@Test
	void getBoardById() {
		// given
		HttpServletRequest request = mock(HttpServletRequest.class);

		// when
		when(boardRepository.findByBoardIdAndIsUseTrue(boardId)).thenReturn(boardEntity);
		BoardGetDto.Response actualResponse = boardService.getBoardById(boardId, request);

		// then
		assertNotNull(actualResponse);
	}

	@DisplayName("게시판 생성 테스트")
	@Test
	void addBoard() {
		// given
		BoardAddDto.Request request = mock(BoardAddDto.Request.class);
		HttpServletRequest servletRequest = mock(HttpServletRequest.class);

		// when
		when(jwtProvider.getHeader(servletRequest)).thenReturn(socialId);
		when(userRepository.findBySocialId(socialId)).thenReturn(Optional.ofNullable(userEntity));
		when(boardRepository.save(any(BoardEntity.class))).thenReturn(boardEntity);
		BoardAddDto.Response actualResponse = boardService.addBoard(request, servletRequest);

		// then
		assertNotNull(actualResponse);
	}

	@DisplayName("게시판 업데이트 테스트")
	@Test
	void updateBoard() {
		// given
		BoardUpdateDto.Request request = mock(BoardUpdateDto.Request.class);
		when(userRepository.findByUserId(userId)).thenReturn(Optional.of(userEntity));
		when(request.getUser()).thenReturn(userEntity);

		// when
		when(boardRepository.save(any(BoardEntity.class))).thenReturn(boardEntity);
		BoardUpdateDto.Response actualResponse = boardService.updateBoard(request);

		// then
		assertNotNull(actualResponse);
	}

	@DisplayName("보드 삭제 테스트")
	@Test
	void deleteBoard() {
		// given
		BoardIsUseDto.Request request = mock(BoardIsUseDto.Request.class);

		// when
		when(boardRepository.save(any(BoardEntity.class))).thenReturn(boardEntity);
		BoardIsUseDto.Response actualResponse = boardService.deleteBoard(request);

		// then
		assertNotNull(actualResponse);
	}

	@DisplayName("게시판 총 갯수 조회")
	@Test
	void getTotalBoardCount() {
		// given
		long expectedCount = 1L;
		when(boardRepository.countByIsUseTrue()).thenReturn(expectedCount);

		// when
		Long actualCount = boardService.getTotalBoardCount();
		// then
		assertNotNull(actualCount);
		assertEquals(expectedCount, actualCount);
	}

	@DisplayName("유저의 게시글 갯수 조회")
	@Test
	void getBoardCount() {
		// given
		HttpServletRequest request = mock(HttpServletRequest.class);
		long expectedCount = 1L;
		when(boardRepository.countByUserUserIdAndIsUseTrue(userId)).thenReturn(expectedCount);
		// when
		when(jwtProvider.getHeader(request)).thenReturn(socialId);
		when(userRepository.findBySocialId(socialId)).thenReturn(Optional.ofNullable(userEntity));
		Long actualCount = boardService.getBoardCount(request);

		// then
		assertNotNull(actualCount);
		assertEquals(expectedCount, actualCount);
	}

}