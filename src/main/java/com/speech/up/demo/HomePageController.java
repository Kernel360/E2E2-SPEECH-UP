package com.speech.up.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.speech.up.board.service.dto.BoardGetDto;

/**
 * HomePageController는 웹 애플리케이션의 주요 페이지를 처리하는 컨트롤러입니다.
 * 이 컨트롤러는 홈페이지, 로그인 페이지, 사용자 페이지, 지도 페이지를 제공합니다.
 */
@Controller
public class HomePageController {

	@Value("${kakao.map.app.key}")
	private String kakaoMapAppKey;

	/**
	 * 홈페이지를 반환합니다.
	 *
	 * @return 홈페이지 템플릿의 이름
	 */
	@GetMapping("/")
	public String home() {
		return "home";
	}

	/**
	 * 로그인 페이지를 반환합니다.
	 *
	 * @return 로그인 페이지 템플릿의 이름
	 */
	@RequestMapping("/login")
	public String login() {
		return "signIn";
	}

	/**
	 * 사용자 개인 페이지를 반환합니다.
	 *
	 * @return 사용자 페이지 템플릿의 이름
	 */
	@GetMapping("/page/me")
	public String myPage() {
		return "myPage";
	}

	/**
	 * 지도 페이지를 반환합니다. Kakao 지도 API 키를 모델에 추가합니다.
	 *
	 * @param model 뷰에 전달할 데이터 모델
	 * @return 지도 페이지 템플릿의 이름
	 */
	@GetMapping("/map")
	public String mapPage(Model model) {
		model.addAttribute("kakaoMapAppKey", kakaoMapAppKey);
		return "map";
	}
}
