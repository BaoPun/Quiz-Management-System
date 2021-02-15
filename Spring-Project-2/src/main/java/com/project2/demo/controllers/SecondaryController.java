package com.project2.demo.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project2.demo.beans.User;
import com.project2.demo.services.DBService;
import com.project2.demo.DAO.UserRepository;
import com.project2.demo.beans.Quiz;
import com.project2.demo.beans.User;
import com.project2.demo.entities.Engine;

@RestController
public class SecondaryController {

	@Autowired
	private DBService services;
  
  @Autowired
	private UserRepository userRepo;
	
	public SecondaryController() {
	}
	
  public class LoginInfo {
		private String username;
		private String password;
	}
	
	@PostMapping(path="/login", consumes= {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<String> login_page(HttpSession session, @RequestParam MultiValueMap<String,String> paramMap) {
		Engine engine = Engine.getGlobalEngine();
		
		System.out.println(session.getId());
		System.out.println(paramMap.getFirst("username"));
		System.out.println(paramMap.getFirst("password"));
		HttpHeaders headers=new HttpHeaders();
		headers.setLocation(URI.create("/bar"));
		return new ResponseEntity<String>(headers,HttpStatus.FOUND);
//		if (engine.login(session.getId(),username,password)) {
//			return new ResponseEntity<String>(HttpStatus.OK);
//		} else {
//			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
//		}
    }
	
	@GetMapping(value="/s/getQuizzes", produces="application/json")
	public List<Quiz> getQuizzes(@RequestParam String student) {
		List<Quiz> retval = new ArrayList<Quiz>();
		Quiz foo = new Quiz();
		foo.setId(3);
		foo.setName("Quiz A");
		foo.setUser(null);
		Quiz foo2 = new Quiz();
		foo2.setId(3);
		foo2.setName("Quiz B");
		foo2.setUser(null);
		retval.add(foo);
		retval.add(foo2);
		return retval;
	}
  
	@GetMapping(value="/thing", produces = "application/json")
	public User gettest() {
		User user = services.getUser("bim");
		List<User> users = services.getAllUsers();
		
		for (User u : users) {
			System.out.println(u.getUsername());
		}
//		User user2 = new User();
//		user2.setUsername("baraz");
//		user2.setTeacher(user);
//		user2.setRole(UserType.STUDENT);
//		user2.setPasswordHash("");
//		services.addThing(user2);
		System.out.println(user.getId());
		//System.out.println(services.getQuiz(4));
		System.out.println(System.identityHashCode(user));
		//System.out.println(System.identityHashCode(user.getTeacher()));
		//System.out.println(System.identityHashCode(user.getTeacher().getTeacher()));
		//System.out.println(System.identityHashCode(user.getTeacher().getTeacher().getTeacher()));
		return null;
	}

}
