package com.speech.up.reply.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.speech.up.reply.entity.ReplyEntity;

public class ReplyRepositoryTest {

	@Mock
	ReplyRepository replyRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("사용 중인 댓글을 replyId로 검색")
	@Test
	public void getReplyByReplyIdTest() {
		//given
		ReplyEntity replyEntity = mock(ReplyEntity.class);
		Long replyId = 1L;

		//when
		when(replyRepository.findByReplyIdAndIsUseTrue(replyId)).thenReturn(replyEntity);

		//then
		assertEquals(replyRepository.findByReplyIdAndIsUseTrue(replyId), replyEntity);
	}

	@DisplayName("replyId로 사용 중인지 여부 확인")
	@Test
	public void existReplyByReplyIdTest() {
		//given
		boolean replyExists = false;
		Long replyId = 1L;

		//when
		when(replyRepository.existsByReplyIdAndIsUseTrue(replyId)).thenReturn(replyExists);

		//then
		assertEquals(replyRepository.existsByReplyIdAndIsUseTrue(replyId), replyExists);
	}

	@DisplayName("boardId로 사용 중인 댓글 모두 검색")
	@Test
	public void getRepliesAllByBoardIdTest() {
		//given
		List<ReplyEntity> replyEntities = Collections.singletonList(mock(ReplyEntity.class));
		Long boardId = 1L;

		//when
		when(replyRepository.findAllByBoardBoardIdAndIsUseTrueOrderByCreatedAtDesc(boardId)).thenReturn(replyEntities);

		//then
		assertEquals(replyRepository.findAllByBoardBoardIdAndIsUseTrueOrderByCreatedAtDesc(boardId), replyEntities);
	}

	@DisplayName("userId로 해당 유저의 댓글 갯수")
	@Test
	public void countRepliesAllByUserIdTest() {
		//given
		Long userId = 1L;
		Long result = 1L;

		//when
		when(replyRepository.countByUserUserIdAndIsUseTrue(userId)).thenReturn(result);

		//then
		assertEquals(result, replyRepository.countByUserUserIdAndIsUseTrue(userId));
	}

}
