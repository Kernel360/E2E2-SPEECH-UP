package com.speech.up.reply.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.speech.up.reply.service.dto.ReplyAddDto;
import com.speech.up.reply.service.dto.ReplyGetDto;
import com.speech.up.reply.service.dto.ReplyIsUseDto;
import com.speech.up.reply.service.dto.ReplyUpdateDto;

import jakarta.servlet.http.HttpServletRequest;

class ReplyServiceTest {

	@Mock
	ReplyService replyService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("리플 다건 조회 테스트")
	@Test
	void getAllReplyLisTest() {
		//given
		Long boardId = 1L;
		List<ReplyGetDto.Response> expectedResponse = Collections.singletonList(mock(ReplyGetDto.Response.class));

		// when
		when(replyService.getAllReplyList(boardId)).thenReturn(expectedResponse);

		// then
		assertEquals(expectedResponse, replyService.getAllReplyList(boardId));
	}

	@DisplayName("리플 단건 조회 테스트")
	@Test
	void getReply() {
		// given
		Long replyId = 1L;
		ReplyGetDto.Response expectedResponse = mock(ReplyGetDto.Response.class);

		// when
		when(replyService.getReply(replyId)).thenReturn(expectedResponse);

		// then
		assertEquals(expectedResponse, replyService.getReply(replyId));
	}

	@DisplayName("리플 추가 테스트")
	@Test
	void addReply() {
		// given
		ReplyAddDto.Request request = mock(ReplyAddDto.Request.class);
		ReplyAddDto.Response response = mock(ReplyAddDto.Response.class);

		// when
		when(replyService.addReply(request)).thenReturn(response);

		// then
		assertEquals(response, replyService.addReply(request));
	}

	@DisplayName("리플 업데이트 테스트")
	@Test
	void updateReply() {
		// given
		ReplyUpdateDto.Request request = mock(ReplyUpdateDto.Request.class);
		ReplyUpdateDto.Response response = mock(ReplyUpdateDto.Response.class);

		// when
		when(replyService.updateReply(request)).thenReturn(response);

		// then
		assertEquals(response, replyService.updateReply(request));
	}

	@DisplayName("리플 삭제 테스트")
	@Test
	void isUseReply() {
		// given
		ReplyIsUseDto.Request request = mock(ReplyIsUseDto.Request.class);
		ReplyIsUseDto.Response response = mock(ReplyIsUseDto.Response.class);

		// when
		when(replyService.isUseReply(request)).thenReturn(response);

		// then
		assertEquals(response, replyService.isUseReply(request));
	}

	@DisplayName("리플 갯수 확인")
	@Test
	void getReplyCount() {
		// given
		HttpServletRequest hasAuthorizationRequest = mock(HttpServletRequest.class);
		HttpServletRequest noHasAuthorizationRequest = mock(HttpServletRequest.class);
		hasAuthorizationRequest.setAttribute("Authorization", "Bearer token");
		Long result = 1L;

		// when
		when(replyService.getReplyCount(hasAuthorizationRequest)).thenReturn(result);
		when(replyService.getReplyCount(noHasAuthorizationRequest)).thenReturn(null);

		// then
		assertEquals(result, replyService.getReplyCount(hasAuthorizationRequest));
		assertNotEquals(result, replyService.getReplyCount(noHasAuthorizationRequest));
	}

}