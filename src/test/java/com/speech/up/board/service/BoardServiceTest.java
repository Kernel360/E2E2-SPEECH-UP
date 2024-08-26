package com.speech.up.board.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.speech.up.auth.provider.JwtProvider;
import com.speech.up.board.entity.BoardEntity;
import com.speech.up.board.repository.BoardRepository;
import com.speech.up.board.service.dto.BoardAddDto;
import com.speech.up.board.service.dto.BoardGetDto;
import com.speech.up.board.service.dto.BoardIsUseDto;
import com.speech.up.board.service.dto.BoardUpdateDto;
import com.speech.up.report.MockBoardEntity;
import com.speech.up.script.mock.MockUser;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;
import com.speech.up.user.service.UserService;

import jakarta.servlet.http.HttpServlet;

class BoardServiceTest {

	private static final Logger log = LoggerFactory.getLogger(BoardServiceTest.class);
	@Mock
	UserRepository userRepository;

	@Mock
	BoardRepository boardRepository;

	@Mock
	JwtProvider jwtProvider;

	@InjectMocks
	BoardService boardService;

	String socialId = "socialId";
	String bearer = "Bearer ";
	String token = bearer + socialId;


	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);


	}

	/*@DisplayName("BoardList 다건 조회")
	@Test
	void getAllBoardList() {
		// 	given
		int page = 5;
		int size = 10;
		BoardGetDto.Response boardGetDto = Mockito.mock(BoardGetDto.Response.class);

		BoardEntity boardEntity = Mockito.mock(BoardEntity.class);
		BoardEntity fistBoardEntity = Mockito.mock(BoardEntity.class);
		given(fistBoardEntity.getBoardId()).willReturn(1L);
		BoardEntity LastBoardEntity = Mockito.mock(BoardEntity.class);
		given(LastBoardEntity.getBoardId()).willReturn(10L);


		List<BoardEntity> boardEntities = List.of(fistBoardEntity,boardEntity,boardEntity,boardEntity,boardEntity,boardEntity,boardEntity,boardEntity,boardEntity,LastBoardEntity);


		List<BoardGetDto.Response> boardGetDtoList;

		Pageable pageable = PageRequest.of(page-1, size);

		Page<BoardEntity> boardList = new PageImpl<>(boardEntities.subList(page*size,Math.min((page + 1) * size, boardEntities.size())),pageable,boardEntities.size());


		given(boardRepository.findAllByIsUseTrueOrderByCreatedAtDesc(any(Pageable.class))).willReturn(boardList);

		// 	when

		boardGetDtoList = boardService.getAllBoardList(page, size);

		// 	then

		verify(boardRepository, times(1)).findAllByIsUseTrueOrderByCreatedAtDesc(any(Pageable.class));
		// assert(boardGetDtoList.get(9).getBoardId().equals(LastBoardEntity.getBoardId()));
		assertThat(boardGetDtoList.size()).isEqualTo(size);

	}*/

	@DisplayName("BoardList 단건 조회")
	@Test
	void getBoardById() {
		// 	given
		UserEntity userEntity = Mockito.mock(UserEntity.class);
		given(userEntity.getUserId()).willReturn(1L);

		BoardEntity boardEntity = Mockito.mock(BoardEntity.class);
		given(boardEntity.getUser()).willReturn(userEntity);

		MockHttpServletRequest httpRequest = new MockHttpServletRequest();
		httpRequest.addHeader("Authorization", token);

		BoardGetDto.Response response;

		given(jwtProvider.validate(socialId)).willReturn(socialId);
		given(userRepository.findBySocialId(socialId)).willReturn(userEntity);
		given(boardRepository.findByBoardIdAndIsUseTrue(1L)).willReturn(boardEntity);

		// 	when

		response = boardService.getBoardById(1L, httpRequest);

		// 	then

		verify(jwtProvider,times(1)).validate(socialId);
		verify(userRepository,times(1)).findBySocialId(socialId);
		verify(boardRepository, times(1)).findByBoardIdAndIsUseTrue(1L);


	}

	@DisplayName("게시판 생성 테스트")
	@Test
	void addBoard() {
		// 	given
		UserEntity userEntity = Mockito.mock(UserEntity.class);

		MockHttpServletRequest httpRequest = new MockHttpServletRequest();
		httpRequest.addHeader("Authorization", "Bearer " + token);
		httpRequest.addHeader("socialId", socialId);

		BoardAddDto.Request request = new BoardAddDto.Request("title", "content", userEntity);

		BoardEntity boardEntity = BoardEntity.create(request);

		BoardAddDto.Response response;

		given(jwtProvider.validate(token)).willReturn(socialId);
		given(userRepository.findBySocialId(socialId)).willReturn(userEntity);
		given(boardRepository.save(any(BoardEntity.class))).willReturn(boardEntity);

		// 	when

		response = boardService.addBoard(request, httpRequest);

		// 	then

		assert (response.getContent()).equals(request.getContent());
		assert (response.getTitle()).equals(request.getTitle());

	}

	@DisplayName("게시판 업데이트 테스트")
	@Test
	void updateBoard() {
		// 	given

		UserEntity userEntity = Mockito.mock(UserEntity.class);

		BoardUpdateDto.Request request = Mockito.mock(BoardUpdateDto.Request.class);
		given(request.getBoardId()).willReturn(1L);
		given(request.getTitle()).willReturn("title");
		given(request.getContent()).willReturn("content");
		given(request.getUser()).willReturn(userEntity);
		BoardUpdateDto.Response response;

		BoardEntity boardEntity = BoardEntity.update(request);

		given(userRepository.findByUserId(request.getUser().getUserId())).willReturn(userEntity);
		given(boardRepository.save(any(BoardEntity.class))).willReturn(boardEntity);
		// 	when

		response = boardService.updateBoard(request);
		// 	then

		assert (response.getContent()).equals(request.getContent());
		assert (response.getTitle()).equals(request.getTitle());
		verify(boardRepository, times(1)).save(any(BoardEntity.class));

	}

	@DisplayName("보드 삭제 테스트")
	@Test
	void deleteBoard() {
		// 	given

		UserEntity userEntity = Mockito.mock(UserEntity.class);

		BoardIsUseDto.Request request = Mockito.mock(BoardIsUseDto.Request.class);
		given(request.getBoardId()).willReturn(1L);
		given(request.isUse()).willReturn(true);
		given(request.getTitle()).willReturn("title");
		given(request.getContent()).willReturn("content");
		given(request.getUser()).willReturn(userEntity);
		BoardEntity boardEntity = BoardEntity.delete(request);
		BoardIsUseDto.Response response;
		given(boardRepository.save(any(BoardEntity.class))).willReturn(boardEntity);

		// 	when

		response = boardService.deleteBoard(request);
		// 	then

		assertFalse(response.isUse(), " isUse의 값이 변경되지 않았습니다.");

	}

	@DisplayName("게시판 총 갯수 조회")
	@Test
	void getTotalBoardCount() {
		// 	given
		Long count = 3L;
		Long result;

		given(boardRepository.countByIsUseTrue()).willReturn(count);



		// 	when
		result = boardService.getTotalBoardCount();

		// 	then
		assertThat(result).isEqualTo(count);

	}

	@DisplayName("유저의 게시글 갯수 조회")
	@Test
	void getBoardCount() {
		// 	given
		Long result;

		UserEntity userEntity = Mockito.mock(UserEntity.class);
		given(userEntity.getUserId()).willReturn(1L);

		MockHttpServletRequest httpRequest = new MockHttpServletRequest();
		httpRequest.addHeader("Authorization", token);

		given(jwtProvider.validate(socialId)).willReturn(socialId);
		given(userRepository.findBySocialId(socialId)).willReturn(userEntity);
		given(boardRepository.countByUserUserIdAndIsUseTrue(userEntity.getUserId())).willReturn(1L);

		// 	when

		result = boardService.getBoardCount(httpRequest);

		// 	then

		assert(result.equals(1L));

	}
}