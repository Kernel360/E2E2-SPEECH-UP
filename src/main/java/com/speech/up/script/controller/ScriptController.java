package com.speech.up.script.controller;

import com.speech.up.script.service.ScriptService;
import com.speech.up.script.service.dto.ScriptAddDto;
import com.speech.up.script.service.dto.ScriptGetDto;
import com.speech.up.script.service.dto.ScriptIsUseDto;
import com.speech.up.script.service.dto.ScriptUpdateDto;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/speech-scripts") // => 도메인 수정 필요 /user/{userId}/speech-scripts ??
@RequiredArgsConstructor
public class ScriptController {
	private final ScriptService scriptService;
	private final HttpSession session;
	/**
	 * 유저의 대본 목록 조회
	 *
	 * @return 대본 리스트 반환
	 */
	@GetMapping("/users/me")
	public ResponseEntity<List<ScriptGetDto.Response>> getScriptAll() {
		Long userId = (Long)session.getAttribute("userId");
		return ResponseEntity.ok(scriptService.getScriptList(userId));
	}

	/**
	 * 대본 하나만 조회
	 * @param scriptId 유저가 가지고 있는 script id
	 * @return scriptGetDto 의 ResponseEntity 로 반환
	 */
	@GetMapping("/users/{userId}/scripts/{scriptId}")
	public ResponseEntity<ScriptGetDto.Response> getScript(@PathVariable Long userId, @PathVariable Long scriptId) {
		return ResponseEntity.ok(scriptService.getScript(userId, scriptId));
	}

	/**
	 * 대본 생성
	 * @param scriptAddRequestDto RequestBody 로 대본작성시 필요한 내용을 받아옴
	 * @return 대본을 user 의 대본 목록에 추가
	 */
	@PostMapping("")
	public ResponseEntity<ScriptAddDto.Response> addScript(
		@RequestBody ScriptAddDto.Request scriptAddRequestDto
	) {
		return ResponseEntity.ok(scriptService.addScript(scriptAddRequestDto));
	}

	/**
	 * 대본 수정 기능
	 *
	 * @param scriptUpdateRequestDto RequestBody 로 수정할 내용을 받아옴
	 * @return 수정된 대본 반환
	 */
	@PatchMapping("")
	public ResponseEntity<ScriptUpdateDto.Response> updateScript(
		@RequestBody ScriptUpdateDto.Request scriptUpdateRequestDto
	) {
		return ResponseEntity.ok(scriptService.updateScript(scriptUpdateRequestDto));
	}

	/**
	 * 대본 삭제 기능
	 *
	 * @param scriptIsUseDtoRequest scriptId 에 해당하는 정보를 모두 삭제
	 * @return 삭제 후 성공시 ok 응답
	 */
	@DeleteMapping("")
	public ResponseEntity<ScriptIsUseDto.Response> deleteScript(
		@RequestBody ScriptIsUseDto.Request scriptIsUseDtoRequest
	) {
		return ResponseEntity.ok(scriptService.deleteScriptById(scriptIsUseDtoRequest));
	} // 테스트 후 적용 안되면 메서드 수정 필요
}
