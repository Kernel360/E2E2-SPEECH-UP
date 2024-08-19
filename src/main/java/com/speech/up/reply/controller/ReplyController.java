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

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {
	private final ReplyService replyService;

	@GetMapping("/{boardId}")
	public ResponseEntity<List<ReplyGetDto.Response>> getReplyByBoardId(@PathVariable Long boardId) {
		return ResponseEntity.ok(replyService.getAllReplyList(boardId));
	}

	/**
	 * 게시글 갯수만 조회
	 *
	 * @return scriptGetDto 의 ResponseEntity 로 반환
	 */
	@GetMapping("/users/count/me")
	public ResponseEntity<Long> getBoardCount(HttpServletRequest request) {
		return ResponseEntity.ok(replyService.getBoardCount(request));
	}


	@PostMapping("")
	public ResponseEntity<ReplyAddDto.Response> addReply(@RequestBody ReplyAddDto.Request replyAddrRequestDto) {
		return ResponseEntity.ok(replyService.addReply(replyAddrRequestDto));
	}

	@PatchMapping("")
	public ResponseEntity<ReplyUpdateDto.Response> updateReply(
		@RequestBody ReplyUpdateDto.Request replyUpdateRequestDto) {
		return ResponseEntity.ok(replyService.updateReply(replyUpdateRequestDto));
	}

	@DeleteMapping("")
	public ResponseEntity<ReplyIsUseDto.Response> isUsedReply(@RequestBody ReplyIsUseDto.Request replyIsUseRequestDto) {
		return ResponseEntity.ok(replyService.isUseReply(replyIsUseRequestDto));
	}

}
