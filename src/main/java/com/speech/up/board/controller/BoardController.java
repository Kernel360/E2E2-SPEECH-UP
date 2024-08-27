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

/**
 * BoardController는 게시판 관련된 API 요청을 처리하는 컨트롤러입니다.
 * <p>
 * 이 클래스는 게시판 목록 조회, 특정 게시글 조회, 게시글 추가, 수정, 삭제 등의 기능을 제공합니다.
 * 각 메서드는 HTTP 요청에 따라 특정 작업을 수행하고, 작업 결과를 클라이언트에게 반환합니다.
 * </p>
 */
@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	/**
	 * 모든 게시글 목록을 페이지네이션하여 조회합니다.
	 *
	 * @param page 요청한 페이지 번호 (0부터 시작)
	 * @param size 페이지당 게시글 수
	 * @return 게시글 목록과 함께 HTTP 상태 200(OK)를 반환
	 */
	@GetMapping("/all")
	public ResponseEntity<List<BoardGetDto.Response>> getBoardAll(
		@RequestParam int page, @RequestParam int size
	) {
		return ResponseEntity.ok(boardService.getAllBoardList(page, size));
	}

	/**
	 * 특정 ID에 해당하는 게시글을 조회합니다.
	 *
	 * @param boardId 조회할 게시글의 ID
	 * @param request HTTP 요청 정보
	 * @return 조회된 게시글과 함께 HTTP 상태 200(OK)를 반환
	 */
	@GetMapping("/{boardId}")
	public ResponseEntity<BoardGetDto.Response> getBoard(
		@PathVariable("boardId") Long boardId,
		HttpServletRequest request
	) {
		return ResponseEntity.ok(boardService.getBoardById(boardId, request));
	}

	/**
	 * 현재 사용자와 연관된 게시글의 총 갯수를 조회합니다.
	 *
	 * @param request HTTP 요청 정보
	 * @return 게시글의 갯수와 함께 HTTP 상태 200(OK)를 반환
	 */
	@GetMapping("/users/counts/me")
	public ResponseEntity<Long> getBoardCount(HttpServletRequest request) {
		return ResponseEntity.ok(boardService.getBoardCount(request));
	}

	/**
	 * 새로운 게시글을 추가합니다.
	 * <p>
	 * 이 메서드는 `GENERAL_USER` 또는 `ADMIN_USER` 역할을 가진 사용자만 접근할 수 있습니다.
	 * </p>
	 *
	 * @param boardAddRequest 추가할 게시글의 정보를 담은 요청 객체
	 * @param request HTTP 요청 정보
	 * @return 추가된 게시글의 정보와 함께 HTTP 상태 200(OK)를 반환
	 */
	@PostMapping("")
	@PreAuthorize("hasAnyRole('GENERAL_USER', 'ADMIN_USER')")
	public ResponseEntity<BoardAddDto.Response> addBoard(
		@RequestBody BoardAddDto.Request boardAddRequest,
		HttpServletRequest request
	) {
		return ResponseEntity.ok(boardService.addBoard(boardAddRequest, request));
	}

	/**
	 * 기존 게시글을 수정합니다.
	 * <p>
	 * 이 메서드는 `GENERAL_USER` 또는 `ADMIN_USER` 역할을 가진 사용자만 접근할 수 있습니다.
	 * </p>
	 *
	 * @param boardUpdateRequest 수정할 게시글의 정보를 담은 요청 객체
	 * @return 수정된 게시글의 정보와 함께 HTTP 상태 200(OK)를 반환
	 */
	@PatchMapping("")
	@PreAuthorize("hasAnyRole('GENERAL_USER', 'ADMIN_USER')")
	public ResponseEntity<BoardUpdateDto.Response> updateBoard(@RequestBody BoardUpdateDto.Request boardUpdateRequest) {
		return ResponseEntity.ok(boardService.updateBoard(boardUpdateRequest));
	}

	/**
	 * 기존 게시글을 삭제(비활성화)합니다.
	 * <p>
	 * 이 메서드는 `GENERAL_USER` 또는 `ADMIN_USER` 역할을 가진 사용자만 접근할 수 있습니다.
	 * </p>
	 *
	 * @param boardIsUseResponse 삭제할 게시글의 정보를 담은 요청 객체
	 * @return 게시글 비활성화 상태와 함께 HTTP 상태 200(OK)를 반환
	 */
	@DeleteMapping("")
	@PreAuthorize("hasAnyRole('GENERAL_USER', 'ADMIN_USER')")
	public ResponseEntity<BoardIsUseDto.Response> deleteBoard(@RequestBody BoardIsUseDto.Request boardIsUseResponse) {
		return ResponseEntity.ok(boardService.deleteBoard(boardIsUseResponse));
	}
}