package com.speech.up.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ScriptPageController {

	@GetMapping("/scripts")
	public String script(){
		return "script";
	}

	@GetMapping("/script-list")
	public String scriptList(){
		return "script-list";
	}

	@GetMapping("/script-write")
	public String scriptWrite(){
		return "script-write";
	}

}
