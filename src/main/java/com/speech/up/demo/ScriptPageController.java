package com.speech.up.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class ScriptPageController {

	@GetMapping("/script")
	public String script(HttpSession httpSession, Model model){
		String socialId = (String) httpSession.getAttribute("socialId");
		boolean isLoggedIn = (socialId != null && !socialId.isEmpty());
		if(!isLoggedIn){
			return "signIn";
		}
		model.addAttribute("isLoggedIn", isLoggedIn);
		return "script";
	}

	@GetMapping("/script-list")
	public String scriptList(HttpSession httpSession, Model model){
		String socialId = (String) httpSession.getAttribute("socialId");
		boolean isLoggedIn = (socialId != null && !socialId.isEmpty());
		if(!isLoggedIn){
			return "signIn";
		}
		model.addAttribute("isLoggedIn", isLoggedIn);
		return "script-list";
	}

	@GetMapping("/script-write")
	public String scriptWrite(HttpSession httpSession, Model model){
		String socialId = (String) httpSession.getAttribute("socialId");
		boolean isLoggedIn = (socialId != null && !socialId.isEmpty());
		if(!isLoggedIn){
			return "signIn";
		}
		model.addAttribute("isLoggedIn", isLoggedIn);
		return "script-write";
	}

}
