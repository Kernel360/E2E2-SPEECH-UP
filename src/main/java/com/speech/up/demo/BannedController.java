package com.speech.up.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BannedController {

	@GetMapping("/banned-page")
	public String bannedPage() {
		return "banned";
	}
}

