package com.speech.up.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * AdminPageController는 관리자의 대시보드 페이지를 처리하는 컨트롤러입니다.
 */

@Controller
@RequestMapping("/admin")
public class AdminPageController {
	/**
	 * 관리 페이지를 반환합니다.
	 *
	 * @return "admin-page" 뷰 이름
	 */
	@GetMapping("/view")
	public String adminPage() {
		return "admin-page";
	}
}
