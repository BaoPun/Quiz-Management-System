package com.project2.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project2.demo.DAO.UserRepository;
import com.project2.demo.beans.User;
import com.project2.demo.beans.UserType;

@RestController
@RequestMapping("users")
public class MainController {

	public MainController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="Bar") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	
	@GetMapping(value="/thing", produces = "application/json")
	public User gettest() {
		User user = userRepo.getUserByName("bim");
//		User user2 = new User();
//		user2.setUsername("baraz");
//		user2.setTeacher(user);
//		user2.setRole(UserType.STUDENT);
//		user2.setPasswordHash("");
//		userRepo.addThing(user2);
		System.out.println(user.getId());
		System.out.println(userRepo.getQuiz(4));
		System.out.println(System.identityHashCode(user));
		System.out.println(System.identityHashCode(user.getTeacher()));
		System.out.println(System.identityHashCode(user.getTeacher().getTeacher()));
		System.out.println(System.identityHashCode(user.getTeacher().getTeacher().getTeacher()));
		return null;
	}


}
