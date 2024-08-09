package com.speech.up.demo;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomePageController {

	@GetMapping("/")
	public String home(HttpSession httpSession, Model model){
		String socialId = (String) httpSession.getAttribute("socialId");
		boolean isLoggedIn = (socialId != null && !socialId.isEmpty());

		model.addAttribute("isLoggedIn", isLoggedIn);
		return "home";
	}

	@RequestMapping("/login")
	public String login(HttpSession httpSession) {
		// 로그인 처리 로직 (사용자 인증을 수행)
		return "signIn";
	}

	@RequestMapping("/log-out")
	public String logout(HttpSession httpSession) {
		httpSession.invalidate(); // 세션 무효화 (로그아웃)
		return "redirect:/";
	}
}
