package com.project2.demo.controllers;

import java.util.ArrayList;
import java.util.List;

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
	
	public class Foo {
		public String text;
		public String otherText;
		public Foo(String text,String otherText) {
			this.text=text;
			this.otherText=otherText;
		}
	}

	@GetMapping("/teacher")
	public String teacher_page(Model model) {
		List<String> studentList=new ArrayList<String>();
		studentList.add("alice");
		studentList.add("bob");
		studentList.add("charlie");
		model.addAttribute("students", studentList);
		return "s/teacher";
	}
}
