package com.project2.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project2.demo.beans.User;
import com.project2.demo.entities.Engine;


//@RestController
@Controller
@RequestMapping("/s")
public class SecureController {
	
	@Autowired
	private Engine engine;

	public SecureController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/grade")
	public String grade_page(Model model) {
		return "s/grade";
	}

	@GetMapping("/question")
	public String question_page(Model model) {
		return "s/question";
	}

	@GetMapping("/quiz_selector")
	public String quiz_selector_page(Model model) {
		return "s/quiz_selector";
	}

	@GetMapping("/teacher")
	public String teacher_page(Model model) {
		List<User> users = engine.getAllUsers();
		List<String> userNames = new ArrayList<String>();
		for (User u: users) {
			userNames.add(u.getUsername());
		}
		model.addAttribute("students", userNames);
		return "s/teacher";
	}
}
