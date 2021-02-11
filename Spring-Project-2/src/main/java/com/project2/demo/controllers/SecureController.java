package com.project2.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/s")
public class SecureController {

	public SecureController() {
		// TODO Auto-generated constructor stub
	}

	
	@GetMapping("grade")
	public String grade(Model model) {
		return "grade";
	}
	
}
