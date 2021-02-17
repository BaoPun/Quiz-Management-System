package com.project2.demo.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="Quizzes")
public class Quiz {
	
	
	@Id
	@SequenceGenerator(name = "quiz_id_generator", allocationSize = 1)
	@GeneratedValue(generator = "quiz_id_generator", strategy = GenerationType.SEQUENCE)
	@Column(name = "quizId", updatable=false)
	private int id;
	

	@Column(name="name")
	private String name;
	
	@ManyToOne						// One user can manage multiple different quizzes
									// while a quiz can only be taken by one person
	@JoinColumn(name = "userId")
	private User user;

	public Quiz() {}
	
	
	public Quiz(String name, User user) {
		this.id = -1;
		this.name = name;
		this.user = user;
	}


	public Quiz(int id, String name, User user) {
		this.id = id;
		this.name = name;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Quiz [id=" + id + ", name=" + name +  ", user=" + user + "]";
	}
	
	
}
