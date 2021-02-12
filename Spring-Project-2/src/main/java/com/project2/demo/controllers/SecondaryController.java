package com.project2.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project2.demo.DAO.UserRepository;
import com.project2.demo.beans.Quiz;
import com.project2.demo.beans.User;

@RestController
public class SecondaryController {

	public SecondaryController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping(value="/login/{username}/{password}", method = RequestMethod.GET, produces="application/json")
    public User login_page(@PathVariable("username") String username, @PathVariable("password") String password) {

        for (User u : userRepo.getAllUsers()) {
            if (u.getUsername().equals(username)) {
                if (u.getPasswordHash().equals(password)) {
                    return u;
                } else {
                    System.out.println("Wrong password");
                }
            }

        }

        return null;
    }
	
	@GetMapping(value="/s/getQuizzes", produces="application/json")
	public List<Quiz> getQuizzes(@RequestParam String student) {
		List<Quiz> retval = new ArrayList<Quiz>();
		Quiz foo = new Quiz();
		foo.setId(3);
		foo.setName("Quiz A");
		foo.setUser(null);
		retval.add(foo);
		return retval;
	}
	
	@GetMapping(value="/thing", produces = "application/json")
	public User gettest() {
		User user = userRepo.getUserByName("bim");
		List<User> users = userRepo.getAllUsers();
		
		for (User u : users) {
			System.out.println(u.getUsername());
		}
//		User user2 = new User();
//		user2.setUsername("baraz");
//		user2.setTeacher(user);
//		user2.setRole(UserType.STUDENT);
//		user2.setPasswordHash("");
//		userRepo.addThing(user2);
		System.out.println(user.getId());
		//System.out.println(userRepo.getQuiz(4));
		System.out.println(System.identityHashCode(user));
		System.out.println(System.identityHashCode(user.getTeacher()));
		System.out.println(System.identityHashCode(user.getTeacher().getTeacher()));
		System.out.println(System.identityHashCode(user.getTeacher().getTeacher().getTeacher()));
		return null;
	}

}
