package com.project2.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project2.demo.beans.User;
import com.project2.demo.entities.Engine;

@Controller
public class MainController {
	
	@Autowired
	private Engine engine;

	public MainController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/")
	public String login_page(Model model) {
		return "index";
	}
	
	@GetMapping("/register")
	public String register_page(Model model) {
		List<User> listOfTeachers = engine.getAllTeachers();
		List<String> teachers = new ArrayList<String>();
		
		for(int i = 0; i < listOfTeachers.size(); i++)
			teachers.add(Integer.toString(listOfTeachers.get(i).getId())+ " " + listOfTeachers.get(i).getUsername());
		
		model.addAttribute("teachers", teachers);
		
		return "register";
	}
	

}
