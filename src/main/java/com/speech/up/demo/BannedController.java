package com.speech.up.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * BannedController는 접근이 제한된 사용자에게 표시되는 페이지를 처리하는 컨트롤러입니다.
 */
@Controller
public class BannedController {
	/**
	 * 접근이 제한된 페이지를 반환합니다.
	 *
	 * @return "banned" 뷰 이름
	 */
	@GetMapping("/banned-page")
	public String bannedPage() {
		return "banned";
	}
}

