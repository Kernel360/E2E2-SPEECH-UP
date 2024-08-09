package com.speech.up.board.controller;

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

import com.speech.up.board.service.BoardService;
import com.speech.up.board.service.dto.BoardAddDto;
import com.speech.up.board.service.dto.BoardGetDto;
import com.speech.up.board.service.dto.BoardIsUseDto;
import com.speech.up.board.service.dto.BoardUpdateDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@GetMapping("/all")
	public ResponseEntity<List<BoardGetDto.Response>> getBoardAll(){
		return ResponseEntity.ok(boardService.getAllBoardList());
	}

	@GetMapping("/{boardId}")
	public ResponseEntity<BoardGetDto.Response> getBoard(@PathVariable("boardId") Long boardId){
		return ResponseEntity.ok(boardService.getBoardById(boardId));
	}

	@PostMapping("")
	public ResponseEntity<BoardAddDto.Response> addBoard(@RequestBody BoardAddDto.Request boardAddRequest){
		return ResponseEntity.ok(boardService.addBoard(boardAddRequest));
	}

	@PatchMapping("")
	public ResponseEntity<BoardUpdateDto.Response>  updateBoard(@RequestBody BoardUpdateDto.Request boardUpdateRequest){
		return ResponseEntity.ok(boardService.updateBoard(boardUpdateRequest));
	}

	@DeleteMapping("")
	public ResponseEntity<BoardIsUseDto.Response> deleteBoard(@RequestBody BoardIsUseDto.Request boardIsUseResponse	){
		return ResponseEntity.ok(boardService.deleteBoard(boardIsUseResponse));
	}

}
