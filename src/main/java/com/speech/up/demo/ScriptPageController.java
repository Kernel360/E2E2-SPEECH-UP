package com.speech.up.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScriptPageController {

	@GetMapping("/script")
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
