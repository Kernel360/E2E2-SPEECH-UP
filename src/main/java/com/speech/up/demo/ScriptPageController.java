package com.speech.up.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScriptPageController {

	@GetMapping("/speech-script")
	public String script(){
		return "script";
	}
}
