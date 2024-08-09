package com.speech.up.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterPageController {

	@GetMapping("/sign-up")
	public String signUp(){
		return "signIn";
	}
}
