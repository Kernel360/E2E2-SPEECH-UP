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

@Controller
@RequiredArgsConstructor
public class BoardPageController {
	private final BoardService boardService;

	@GetMapping("/board")
	public String boards(@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "10") int size, Model model){
		List<BoardGetDto.Response> boardList = boardService.getAllBoardList(page, size);
		// 현재 페이지와 페이지 사이즈를 모델에 추가
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageNumber", page);
		model.addAttribute("pageSize", size);

		int totalPages = (int) Math.ceil((double) boardService.getTotalBoardCount() / size);
		model.addAttribute("totalPages", totalPages);
		return "board";
	}
	@GetMapping("/board/{boardId}")
	public String boardsDetail(@PathVariable Long boardId,  Model model, HttpServletRequest request){
	  BoardGetDto.Response boardDetail = boardService.getBoardById(boardId, request);

		model.addAttribute("board", boardDetail);
		return "board-detail";
	}
	@GetMapping("/board/{boardId}/edit")
	public String boardModify(@PathVariable Long boardId,  Model model, HttpServletRequest request){
	  BoardGetDto.Response boardDetail = boardService.getBoardById(boardId, request);

		model.addAttribute("board", boardDetail);
		return "board-write";
	}


}
