package com.speech.up.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

/**
 * ScriptPageController 는 스크립트 관련 페이지 요청을 처리하는 컨트롤러입니다.
 * 이 컨트롤러는 스크립트 목록, 스크립트 작성, 스크립트 페이지 등을 제공합니다.
 */
@Controller
@RequiredArgsConstructor
public class ScriptPageController {

	/**
	 * 스크립트 페이지를 반환합니다.
	 *
	 * @return 스크립트 페이지의 템플릿 이름
	 */
	@GetMapping("/scripts")
	public String script() {
		return "script";
	}

	/**
	 * 스크립트 목록 페이지를 반환합니다.
	 *
	 * @return 스크립트 목록 페이지의 템플릿 이름
	 */
	@GetMapping("/scripts-list")
	public String scriptList() {
		return "script-list";
	}

	/**
	 * 스크립트 작성 페이지를 반환합니다.
	 *
	 * @return 스크립트 작성 페이지의 템플릿 이름
	 */
	@GetMapping("/script-write")
	public String scriptWrite() {
		return "script-write";
	}
}
