package com.project2.demo.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public String login_page(Model model, HttpSession session) {
		engine.removeLoggedUser(session.getId());
		return "index";
	}
	
	@GetMapping("/register")
	public String register_page(Model model) {
		List<User> listOfTeachers = engine.getAllTeachers();
		List<String> teachers = new ArrayList<String>();
		
		for(int i = 0; i < listOfTeachers.size(); i++)
			teachers.add(Integer.toString(listOfTeachers.get(i).getId()) + " " + listOfTeachers.get(i).getUsername());
		
		model.addAttribute("teachers", teachers);
		
		return "register";
	}
	
	// Let the user know that they successfully registered
	@GetMapping(value="/registerSuccess")
	public String registerSuccess(Model model){
		return "register_success";
	}
	
	// Let the user know that they could not register
	@GetMapping(value="/registerFailure")
	public String registerFailure(Model model){
		return "register_failure";
	}
		
	

}
