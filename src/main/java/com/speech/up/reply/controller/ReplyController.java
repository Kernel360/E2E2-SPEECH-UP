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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/replys")
@RequiredArgsConstructor

public class ReplyController {
	private final ReplyService replySerchvice;

	//다건
	@GetMapping("/all")
	public ResponseEntity<List<ReplyGetDto.Response>> getReply() {
		return ResponseEntity.ok(replyService.getAllReplyList());
	}

	//단건
	@GetMapping("/{replyId}")
	public ResponseEntity<ReplyGetDto.Response> getReply(@PathVariable Long replyId) {

		return ResponseEntity.ok(replyService.getReply(replyId));
	}

	@PostMapping("")
	public ResponseEntity<ReplyAddDto.Response> addReply(@RequestBody ReplyAddDto.Request replyAddrequestDto) {

		return ResponseEntity.ok(replyService.addReply(replyAddrequestDto));
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
