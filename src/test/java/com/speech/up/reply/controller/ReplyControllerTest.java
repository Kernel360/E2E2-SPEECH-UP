package com.speech.up.reply.controller;

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

import com.speech.up.reply.service.ReplyService;
import com.speech.up.reply.service.dto.ReplyAddDto;
import com.speech.up.reply.service.dto.ReplyGetDto;
import com.speech.up.reply.service.dto.ReplyIsUseDto;
import com.speech.up.reply.service.dto.ReplyUpdateDto;

import jakarta.servlet.http.HttpServletRequest;

public class ReplyControllerTest {

	@Mock
	ReplyService replyService;

	@InjectMocks
	ReplyController replyController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("특정 게시판 ID에 해당하는 모든 댓글을 조회합니다.")
	@Test
	void getReplyByBoardIdTest(){
		//given
		Long boardId = 1L;

		//when
		ResponseEntity<List<ReplyGetDto.Response>> actualResponse = replyController.getReplyByBoardId(boardId);

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("특정 게시판 ID에 해당하는 모든 댓글을 조회합니다.")
	@Test
	void getReplyCountTest(){
		//given
		HttpServletRequest request = mock(HttpServletRequest.class);

		//when
		ResponseEntity<Long> actualResponse = replyController.getReplyCount(request);

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("특정 게시판 ID에 해당하는 모든 댓글을 조회합니다.")
	@Test
	void addReplyTest(){
		//given
		ReplyAddDto.Request request = mock(ReplyAddDto.Request.class);

		//when
		ResponseEntity<ReplyAddDto.Response> actualResponse = replyController.addReply(request);

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("특정 게시판 ID에 해당하는 모든 댓글을 조회합니다.")
	@Test
	void updateReplyTest(){
		//given
		ReplyUpdateDto.Request request = mock(ReplyUpdateDto.Request.class);

		//when
		ResponseEntity<ReplyUpdateDto.Response> actualResponse = replyController.updateReply(request);

		//then
		assertNotNull(actualResponse);
	}

	@DisplayName("특정 게시판 ID에 해당하는 모든 댓글을 조회합니다.")
	@Test
	void isUsedReplyTest(){
		//given
		ReplyIsUseDto.Request request = mock(ReplyIsUseDto.Request.class);

		//when
		ResponseEntity<ReplyIsUseDto.Response> actualResponse = replyController.isUsedReply(request);

		//then
		assertNotNull(actualResponse);

	}
}
