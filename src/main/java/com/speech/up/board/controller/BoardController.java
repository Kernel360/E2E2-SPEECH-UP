package com.speech.up.board.controller;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.speech.up.board.service.BoardService;
import com.speech.up.board.service.dto.BoardAddDto;
import com.speech.up.board.service.dto.BoardGetDto;
import com.speech.up.board.service.dto.BoardIsUseDto;
import com.speech.up.board.service.dto.BoardUpdateDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@GetMapping("/all")
	public ResponseEntity<List<BoardGetDto.Response>> getBoardAll(
		@RequestParam int page, @RequestParam int size
	) {
		return ResponseEntity.ok(boardService.getAllBoardList(page, size));
	}

	@GetMapping("/{boardId}")
	public ResponseEntity<BoardGetDto.Response> getBoard(@PathVariable("boardId") Long boardId, HttpServletRequest request){
		return ResponseEntity.ok(boardService.getBoardById(boardId, request));
	}

	/**
	 * 게시글 갯수만 조회
	 *
	 * @return scriptGetDto 의 ResponseEntity 로 반환
	 */
	@GetMapping("/users/counts/me")
	public ResponseEntity<Long> getBoardCount(HttpServletRequest request) {
		return ResponseEntity.ok(boardService.getBoardCount(request));
	}

	@PostMapping("")
	@PreAuthorize("hasAnyRole('GENERAL_USER', 'ADMIN_USER')")
	public ResponseEntity<BoardAddDto.Response> addBoard(@RequestBody BoardAddDto.Request boardAddRequest, HttpServletRequest request){
		return ResponseEntity.ok(boardService.addBoard(boardAddRequest,request));
	}

	@PatchMapping("")
	@PreAuthorize("hasAnyRole('GENERAL_USER', 'ADMIN_USER')")
	public ResponseEntity<BoardUpdateDto.Response>  updateBoard(@RequestBody BoardUpdateDto.Request boardUpdateRequest){
		return ResponseEntity.ok(boardService.updateBoard(boardUpdateRequest));
	}

	@DeleteMapping("")
	@PreAuthorize("hasAnyRole('GENERAL_USER', 'ADMIN_USER')")
	public ResponseEntity<BoardIsUseDto.Response> deleteBoard(@RequestBody BoardIsUseDto.Request boardIsUseResponse	){
		return ResponseEntity.ok(boardService.deleteBoard(boardIsUseResponse));
	}

}
