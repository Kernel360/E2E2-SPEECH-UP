package com.speech.up.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.speech.up.board.service.BoardService;
import com.speech.up.board.service.dto.BoardGetDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

/**
 * BoardPageController는 게시판과 관련된 웹 페이지를 처리하는 컨트롤러입니다.
 * 이 컨트롤러는 게시판 목록 조회, 게시판 상세 조회, 게시판 작성 페이지 및 수정 페이지를 제공합니다.
 */
@Controller
@RequiredArgsConstructor
public class BoardPageController {

	private final BoardService boardService;

	/**
	 * 게시판 목록 페이지를 반환합니다.
	 * 페이지 번호와 페이지 사이즈를 요청 파라미터로 받아, 해당 페이지의 게시판 목록을 조회합니다.
	 *
	 * @param page 현재 페이지 번호 (기본값: 1)
	 * @param size 페이지 사이즈 (기본값: 10)
	 * @param model 뷰에 전달할 데이터 모델
	 * @return 게시판 목록 페이지의 이름
	 */
	@GetMapping("/boards")
	public String boards(
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "10") int size,
		Model model
	) {
		List<BoardGetDto.Response> boardList = boardService.getAllBoardList(page, size);
		// 현재 페이지와 페이지 사이즈를 모델에 추가
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageNumber", page);
		model.addAttribute("pageSize", size);

		int totalPages = (int) Math.ceil((double) boardService.getTotalBoardCount() / size);
		model.addAttribute("totalPages", totalPages);
		return "board";
	}

	/**
	 * 게시판 작성 페이지를 반환합니다.
	 *
	 * @return 게시판 작성 페이지의 이름
	 */
	@GetMapping("/boards/write")
	public String boardsWrite() {
		return "board-write";
	}

	/**
	 * 특정 게시판의 상세 정보를 조회하여 상세 페이지를 반환합니다.
	 *
	 * @param boardId 게시판의 ID
	 * @param model 뷰에 전달할 데이터 모델
	 * @param request HTTP 요청 객체
	 * @return 게시판 상세 페이지의 이름
	 */
	@GetMapping("/boards/{boardId}")
	public String boardsDetail(
		@PathVariable Long boardId,
		Model model,
		HttpServletRequest request
	) {
		BoardGetDto.Response boardDetail = boardService.getBoardById(boardId, request);
		model.addAttribute("board", boardDetail);
		return "board-detail";
	}

	/**
	 * 특정 게시판을 수정하기 위한 페이지를 반환합니다.
	 *
	 * @param boardId 게시판의 ID
	 * @param model 뷰에 전달할 데이터 모델
	 * @param request HTTP 요청 객체
	 * @return 게시판 수정 페이지의 이름
	 */
	@GetMapping("/boards/{boardId}/edit")
	public String boardModify(
		@PathVariable Long boardId,
		Model model,
		HttpServletRequest request
	) {
		BoardGetDto.Response boardDetail = boardService.getBoardById(boardId, request);
		model.addAttribute("board", boardDetail);
		return "board-edit";
	}
}
