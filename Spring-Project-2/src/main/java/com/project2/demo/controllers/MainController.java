package com.project2.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	public MainController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/")
	public String login_page(Model model) {
		return "index";
	}


}
