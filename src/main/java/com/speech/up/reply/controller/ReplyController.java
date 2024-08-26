package com.speech.up.reply.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.speech.up.reply.service.ReplyService;
import com.speech.up.reply.service.dto.ReplyAddDto;
import com.speech.up.reply.service.dto.ReplyGetDto;
import com.speech.up.reply.service.dto.ReplyIsUseDto;
import com.speech.up.reply.service.dto.ReplyUpdateDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

/**
 * ReplyController는 댓글과 관련된 API 엔드포인트를 제공합니다.
 * 이 컨트롤러는 댓글의 조회, 추가, 수정 및 사용 여부를 처리합니다.
 */
@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {

	private final ReplyService replyService;

	/**
	 * 특정 게시판 ID에 해당하는 모든 댓글을 조회합니다.
	 *
	 * @param boardId 댓글을 조회할 게시판의 ID
	 * @return 게시판에 속한 댓글 목록
	 */
	@GetMapping("/{boardId}")
	public ResponseEntity<List<ReplyGetDto.Response>> getReplyByBoardId(@PathVariable Long boardId) {
		return ResponseEntity.ok(replyService.getAllReplyList(boardId));
	}

	/**
	 * 현재 사용자의 게시글 개수를 조회합니다.
	 *
	 * @param request HTTP 요청 객체
	 * @return 사용자의 게시글 개수
	 */
	@GetMapping("/users/counts/me")
	public ResponseEntity<Long> getBoardCount(HttpServletRequest request) {
		return ResponseEntity.ok(replyService.getBoardCount(request));
	}

	/**
	 * 새로운 댓글을 추가합니다.
	 *
	 * @param replyAddrRequestDto 추가할 댓글의 정보가 담긴 요청 객체
	 * @return 추가된 댓글의 세부 정보
	 */
	@PostMapping("")
	public ResponseEntity<ReplyAddDto.Response> addReply(@RequestBody ReplyAddDto.Request replyAddrRequestDto) {
		return ResponseEntity.ok(replyService.addReply(replyAddrRequestDto));
	}

	/**
	 * 댓글을 수정합니다.
	 *
	 * @param replyUpdateRequestDto 수정할 댓글의 정보가 담긴 요청 객체
	 * @return 수정된 댓글의 세부 정보
	 */
	@PatchMapping("")
	public ResponseEntity<ReplyUpdateDto.Response> updateReply(
		@RequestBody ReplyUpdateDto.Request replyUpdateRequestDto) {
		return ResponseEntity.ok(replyService.updateReply(replyUpdateRequestDto));
	}

	/**
	 * 댓글의 사용 여부를 변경합니다. 실제로 데이터를 삭제하지 않고, 사용 상태를 변경합니다.
	 *
	 * @param replyIsUseRequestDto 사용 상태를 변경할 댓글의 정보가 담긴 요청 객체
	 * @return 사용 상태로 변경된 댓글의 세부 정보
	 */
	@DeleteMapping("")
	public ResponseEntity<ReplyIsUseDto.Response> isUsedReply(@RequestBody ReplyIsUseDto.Request replyIsUseRequestDto) {
		return ResponseEntity.ok(replyService.isUseReply(replyIsUseRequestDto));
	}
}
