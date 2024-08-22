package com.speech.up.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.speech.up.board.service.dto.BoardGetDto;

@Controller
public class HomePageController {

	@GetMapping("/")
	public String home(){
		return "home";
	}

	@RequestMapping("/login")
	public String login() {
		return "signIn";
	}

	@GetMapping("/page/me")
	public String myPage(){
		return "myPage";
	}

	@Value("${kakao.map.app.key}")
	private String kakaoMapAppKey;

	@GetMapping("/map")
	public String mapPage(Model model) {
		model.addAttribute("kakaoMapAppKey", kakaoMapAppKey);
		return "map";
	}
}
