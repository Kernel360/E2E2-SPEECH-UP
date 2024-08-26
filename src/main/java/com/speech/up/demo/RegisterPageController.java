package com.speech.up.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * RegisterPageController는 사용자 등록 페이지와 관련된 요청을 처리하는 컨트롤러입니다.
 * 이 컨트롤러는 사용자 등록 페이지를 제공하며, 사용자 가입을 위한 뷰를 반환합니다.
 */
@Controller
public class RegisterPageController {

	/**
	 * 사용자 등록 페이지를 반환합니다.
	 * 이 페이지는 사용자 가입을 위한 폼을 제공하며, `/sign-up` 경로에 매핑됩니다.
	 *
	 * @return 사용자 등록 페이지 템플릿의 이름
	 */
	@GetMapping("/sign-up")
	public String signUp() {
		return "signIn";
	}
}
