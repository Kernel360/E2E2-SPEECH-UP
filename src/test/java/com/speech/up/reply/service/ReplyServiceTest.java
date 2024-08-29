package com.speech.up.reply.service;

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

import com.speech.up.auth.provider.JwtProvider;
import com.speech.up.board.entity.BoardEntity;
import com.speech.up.board.repository.BoardRepository;
import com.speech.up.reply.entity.ReplyEntity;
import com.speech.up.reply.repository.ReplyRepository;
import com.speech.up.reply.service.dto.ReplyAddDto;
import com.speech.up.reply.service.dto.ReplyGetDto;
import com.speech.up.reply.service.dto.ReplyIsUseDto;
import com.speech.up.reply.service.dto.ReplyUpdateDto;
import com.speech.up.user.entity.UserEntity;
import com.speech.up.user.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

class ReplyServiceTest {

	@Mock
	ReplyRepository replyRepository;
	@Mock
	UserRepository userRepository;
	@Mock
	BoardRepository boardRepository;
	@Mock
	JwtProvider jwtProvider;
	@Mock
	UserEntity userEntity;
	@Mock
	BoardEntity boardEntity;
	@Mock
	ReplyEntity replyEntity;

	@InjectMocks
	ReplyService replyService;

	Long boardId;
	Long replyId;
	Long userId;
	String socialId;
	String content;
	String name;
	LocalDateTime createdAt;
	LocalDateTime modifiedAt;
	boolean isUse;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		boardId = 1L;
		replyId = 2L;
		userId = 3L;
		createdAt = LocalDateTime.now();
		modifiedAt = LocalDateTime.now();
		isUse = true;
		content = "This is a test reply";
		name = "This is a test reply";
		socialId = "mockSocialId";

		boardEntity = mock(BoardEntity.class);
		userEntity = mock(UserEntity.class);
		replyEntity = mock(ReplyEntity.class);
		when(userEntity.getUserId()).thenReturn(userId);
		when(userEntity.getSocialId()).thenReturn(socialId);
		when(boardEntity.getBoardId()).thenReturn(boardId);
		when(boardEntity.getUser()).thenReturn(userEntity);
		when(replyEntity.getReplyId()).thenReturn(replyId);
		when(replyEntity.getCreatedAt()).thenReturn(createdAt);
		when(replyEntity.getModifiedAt()).thenReturn(modifiedAt);
		when(replyEntity.getIsUse()).thenReturn(isUse);
		when(replyEntity.getContent()).thenReturn(content);
		when(replyEntity.getName()).thenReturn(name);
		when(replyEntity.getUser()).thenReturn(userEntity);
		when(replyEntity.getBoard()).thenReturn(boardEntity);

		when(userRepository.existsById(userId)).thenReturn(true);
		when(boardRepository.existsById(boardId)).thenReturn(true);
		when(replyRepository.existsById(replyId)).thenReturn(true);
		when(replyRepository.existsByReplyIdAndIsUseTrue(replyId)).thenReturn(true);
	}

	@DisplayName("리플 다건 조회 테스트")
	@Test
	void getAllReplyLisTest() {
		//given
		List<ReplyEntity> list = Arrays.asList(replyEntity, replyEntity);
		when(replyRepository.findAllByBoardBoardIdAndIsUseTrueOrderByCreatedAtDesc(boardId)).thenReturn(list);

		// when
		List<ReplyGetDto.Response> actualResponse = replyService.getAllReplyList(boardId);

		// then
		assertEquals(2, actualResponse.size());
	}

	@DisplayName("리플 단건 조회 테스트")
	@Test
	void getReply() {
		// given
		when(replyRepository.findByReplyIdAndIsUseTrue(replyId)).thenReturn(replyEntity);
		// when
		ReplyGetDto.Response actualResponse = replyService.getReply(replyId);

		// then
		assertEquals(ReplyGetDto.Response.totResponse(replyEntity).getReplyId(), actualResponse.getReplyId());
	}

	@DisplayName("리플 추가 테스트")
	@Test
	void addReply() {
		// given
		ReplyAddDto.Request request = mock(ReplyAddDto.Request.class);
		when(request.getUser()).thenReturn(userEntity);

		// when
		when(replyRepository.save(any(ReplyEntity.class))).thenReturn(replyEntity);
		ReplyAddDto.Response actualResponse = replyService.addReply(request);

		// then
		assertEquals(ReplyAddDto.toResponse(replyEntity).getContent(), actualResponse.getContent());
	}

	@DisplayName("리플 업데이트 테스트")
	@Test
	void updateReply() {
		// Given
		ReplyUpdateDto.Request request = mock(ReplyUpdateDto.Request.class);
		when(request.getUser()).thenReturn(userEntity);
		when(request.getBoard()).thenReturn(boardEntity);
		when(request.getReplyId()).thenReturn(replyId);
		when(replyRepository.save(any(ReplyEntity.class))).thenReturn(replyEntity);
		when(replyEntity.getContent()).thenReturn("Updated content");

		// When
		ReplyUpdateDto.Response expectedResponse = ReplyUpdateDto.toResponse(replyEntity);
		ReplyUpdateDto.Response actualResponse = replyService.updateReply(request);

		// Then
		assertNotNull(actualResponse);
		assertEquals(expectedResponse.getContent(), actualResponse.getContent());
		verify(replyRepository, times(1)).save(any(ReplyEntity.class));
		verify(replyRepository, times(1)).existsById(replyId);
		verify(replyRepository, times(1)).existsByReplyIdAndIsUseTrue(replyId);
		verify(userRepository, times(1)).existsById(userId);
		verify(boardRepository, times(1)).existsById(boardId);
	}

	@DisplayName("리플 삭제 테스트")
	@Test
	void isUseReply() {
		// given
		ReplyIsUseDto.Request request = mock(ReplyIsUseDto.Request.class);
		when(request.getUser()).thenReturn(userEntity);
		when(request.getBoard()).thenReturn(boardEntity);
		when(request.getReplyId()).thenReturn(replyId);
		when(replyRepository.save(any(ReplyEntity.class))).thenReturn(replyEntity);

		// when
		ReplyIsUseDto.Response expectedResponse = ReplyIsUseDto.toResponse(replyEntity);
		ReplyIsUseDto.Response actualResponse = replyService.isUseReply(request);

		// then
		assertNotNull(actualResponse);
		assertEquals(expectedResponse.getContent(), actualResponse.getContent());
	}

	@DisplayName("리플 갯수 확인")
	@Test
	void getReplyCount() {
		// given
		HttpServletRequest servletRequest = mock(HttpServletRequest.class);
		Long requestResult = 1L;
		when(jwtProvider.getHeader(servletRequest)).thenReturn(socialId);
		when(userRepository.findBySocialId(socialId)).thenReturn(Optional.ofNullable(userEntity));
		when(replyRepository.countByUserUserIdAndIsUseTrue(userId)).thenReturn(requestResult);

		// when
		Long expectedCount = 1L;
		Long actualCount = replyService.getReplyCount(servletRequest);

		// then
		assertEquals(expectedCount, actualCount);
	}

}