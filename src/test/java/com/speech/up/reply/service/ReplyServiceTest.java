package com.speech.up.reply.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;

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

class ReplyServiceTest {

	@Mock
	ReplyRepository replyRepository;

	@Mock
	UserRepository userRepository;

	@Mock
	BoardRepository boardRepository;

	@Mock
	JwtProvider jwtProvider;

	@InjectMocks
	ReplyService replyService;

	String socialId = "socialId";
	String bearer = "Bearer ";
	String token = bearer + socialId;

	UserEntity userEntity;
	ReplyEntity replyEntity;
	BoardEntity boardEntity;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		userEntity = Mockito.mock(UserEntity.class);
		given(userEntity.getUserId()).willReturn(1L);
		given(userEntity.getName()).willReturn("name");
		given(userEntity.getSocialId()).willReturn("socialId");
		given(userEntity.getEmail()).willReturn("email");
		given(userEntity.getLevel()).willReturn("level");
		given(userEntity.getAuthorization()).willReturn(token);

		boardEntity = Mockito.mock(BoardEntity.class);
		given(boardEntity.getBoardId()).willReturn(1L);
		given(boardEntity.getTitle()).willReturn("title");
		given(boardEntity.getContent()).willReturn("content");
		given(boardEntity.getIsUse()).willReturn(true);
		given(boardEntity.getUser()).willReturn(userEntity);

		replyEntity = Mockito.mock(ReplyEntity.class);
		given(replyEntity.getReplyId()).willReturn(1L);
		given(replyEntity.getIsUse()).willReturn(true);
		given(replyEntity.getUser()).willReturn(userEntity);
		given(replyEntity.getContent()).willReturn("content");
		given(replyEntity.getName()).willReturn("name");
		given(replyEntity.getBoard()).willReturn(boardEntity);

	}

	@DisplayName("리플 다건 조회 테스트")
	@Test
	void getAllReplyList() {
		// given
		Long boardId = 1L;

		List<ReplyEntity> replyEntities = List.of(replyEntity,replyEntity);

		ReplyGetDto.Response replyGetResponse = Mockito.mock(ReplyGetDto.Response.class);
		given(replyGetResponse.getReplyId()).willReturn(1L);
		given(replyGetResponse.getName()).willReturn("name");
		given(replyGetResponse.getContent()).willReturn("content");
		given(replyGetResponse.getBoardId()).willReturn(1L);
		given(replyGetResponse.getUserId()).willReturn(1L);

		List<ReplyGetDto.Response> replyGetResponseList;

		given(replyRepository.findAllByBoardBoardIdAndIsUseTrueOrderByCreatedAtDesc(boardId)).willReturn(replyEntities);

		// when

		replyGetResponseList = replyService.getAllReplyList(boardId);
		//then

		assert(replyGetResponseList.size() == replyEntities.size());
		verify(replyRepository,times(1)).findAllByBoardBoardIdAndIsUseTrueOrderByCreatedAtDesc(boardId);


	}

	@DisplayName("리플 단건 조회 테스트")
	@Test
	void getReply() {
		// given
		Long replyId = 1L;

		given(replyRepository.findByReplyIdAndIsUseTrue(replyId)).willReturn(replyEntity);

		ReplyGetDto.Response replyGetResponse;

		// when

		replyGetResponse = replyService.getReply(replyId);

		// then
		assert (replyGetResponse.getReplyId()).equals(replyId);
		verify(replyRepository, times(1)).findByReplyIdAndIsUseTrue(replyId);

	}

	@DisplayName("리플 추가 테스트")
	@Test
	void addReply() {
		// given

		ReplyAddDto.Request replyAddRequest = Mockito.mock(ReplyAddDto.Request.class);
		given(replyAddRequest.getBoard()).willReturn(boardEntity);
		given(replyAddRequest.getUser()).willReturn(userEntity);
		given(replyAddRequest.getContent()).willReturn("content");

		ReplyAddDto.Response replyAddResponse;

		given(replyRepository.save(any(ReplyEntity.class))).willReturn(replyEntity);

		// when
		replyAddResponse = replyService.addReply(replyAddRequest);

		// then
		assert (replyAddResponse.getContent()).equals(replyAddRequest.getContent());
		verify(replyRepository, times(1)).save(any(ReplyEntity.class));
	}

	@DisplayName("리플 업데이트 테스트")
	@Test
	void updateReply() {
		// 	given

		ReplyUpdateDto.Request replyUpdateRequest = Mockito.mock(ReplyUpdateDto.Request.class);
		given(replyUpdateRequest.getReplyId()).willReturn(1L);
		given(replyUpdateRequest.getBoard()).willReturn(boardEntity);
		given(replyUpdateRequest.getUser()).willReturn(userEntity);
		given(replyUpdateRequest.getContent()).willReturn("content");

		ReplyUpdateDto.Response response;

		given(replyRepository.save(any(ReplyEntity.class))).willReturn(replyEntity);
		given(userRepository.existsById(any())).willReturn(true);
		given(boardRepository.existsById(any(Long.class))).willReturn(true);
		given(replyRepository.existsById(any(Long.class))).willReturn(true);
		given(replyRepository.existsByReplyIdAndIsUseTrue(any(long.class))).willReturn(true);

		// 	when

		response = replyService.updateReply(replyUpdateRequest);

		// 	then

		assertNotNull(response);
		assert (response.getReplyId()).equals(replyUpdateRequest.getReplyId());
		verify(replyRepository, times(1)).save(any(ReplyEntity.class));
		verify(userRepository, times(1)).existsById(any(Long.class));
		verify(boardRepository, times(1)).existsById(any(Long.class));
		verify(replyRepository, times(1)).existsById(any(Long.class));
		verify(replyRepository, times(1)).existsByReplyIdAndIsUseTrue(any(long.class));

	}

	@DisplayName("리플 삭제 테스트")
	@Test
	void isUseReply() {
		// given

		ReplyEntity modifiedReplyEntity = Mockito.mock(ReplyEntity.class);
		given(modifiedReplyEntity.getReplyId()).willReturn(1L);
		given(modifiedReplyEntity.getIsUse()).willReturn(false);
		given(modifiedReplyEntity.getUser()).willReturn(userEntity);
		given(modifiedReplyEntity.getContent()).willReturn("content");
		given(modifiedReplyEntity.getName()).willReturn("name");

		ReplyIsUseDto.Request replyIsUseRequest = Mockito.mock(ReplyIsUseDto.Request.class);
		given(replyIsUseRequest.getReplyId()).willReturn(1L);
		given(replyIsUseRequest.getIsUse()).willReturn(true);
		given(replyIsUseRequest.getUser()).willReturn(userEntity);
		given(replyIsUseRequest.getContent()).willReturn("content");
		given(replyIsUseRequest.getBoard()).willReturn(boardEntity);

		given(replyRepository.save(any(ReplyEntity.class))).willReturn(modifiedReplyEntity);

		given(userRepository.existsById(any(Long.class))).willReturn(true);
		given(boardRepository.existsById(any(Long.class))).willReturn(true);
		given(replyRepository.existsById(any(Long.class))).willReturn(true);
		given(replyRepository.existsByReplyIdAndIsUseTrue(any(long.class))).willReturn(true);

		ReplyIsUseDto.Response response;

		// when

		response = replyService.isUseReply(replyIsUseRequest);

		// then

		assertNotNull(response);
		assert (response.getReplyId()).equals(replyEntity.getReplyId());
		assert (response.getIsUse()).equals(false);
		verify(replyRepository).save(any(ReplyEntity.class));
		verify(userRepository, times(1)).existsById(any(Long.class));
		verify(boardRepository, times(1)).existsById(any(Long.class));
		verify(replyRepository, times(1)).existsById(any(Long.class));
		verify(replyRepository, times(1)).existsByReplyIdAndIsUseTrue(any(long.class));
	}

	@Test
	void checkReply() {
	}

	@Test
	void checkUserEntity() {
	}

	@Test
	void checkBoardEntity() {
	}

	@Test
	void checkReplyEntity() {
		// given

		// when

		// then

	}

	@DisplayName("리플 갯수 확인")
	@Test
	void getReplyCount() {
		// given
		Long result;
		MockHttpServletRequest httpRequest = new MockHttpServletRequest();
		httpRequest.addHeader("Authorization", token);

		given(jwtProvider.validate(socialId)).willReturn(socialId);
		given(userRepository.findBySocialId(socialId)).willReturn(userEntity);
		given(replyRepository.countByUserUserIdAndIsUseTrue(userEntity.getUserId())).willReturn(1L);
		// when
		result = replyService.getReplyCount(httpRequest);
		// then
		assertNotNull(result);
		assert (result.equals(userEntity.getUserId()));
		verify(jwtProvider, times(1)).validate(socialId);
		verify(userRepository, times(1)).findBySocialId(socialId);
		verify(replyRepository, times(1)).countByUserUserIdAndIsUseTrue(userEntity.getUserId());
	}
}