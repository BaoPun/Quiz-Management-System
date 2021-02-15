package com.project2.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project2.demo.DAO.DBRepoImpl;
import com.project2.demo.beans.User;
import com.project2.demo.services.DBService;

@Controller
public class MainController {
	
	@Autowired
	private DBService services;

	public MainController() {
		// TODO Auto-generated constructor stub
	}
	
//	@Autowired
//	private UserRepository userRepo;
	
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="Bar") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	
	@GetMapping("/login")
	public String login_page(Model model) {
		return "login";
	}


}
