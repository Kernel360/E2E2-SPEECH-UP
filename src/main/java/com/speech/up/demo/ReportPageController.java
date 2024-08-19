package com.speech.up.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportPageController {

	@GetMapping("/report")
	public String reportPage(){
		return "report";
	}
}
