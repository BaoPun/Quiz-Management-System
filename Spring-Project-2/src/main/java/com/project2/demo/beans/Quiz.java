package com.project2.demo.beans;

import com.project2.demo.beans.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="quizzes")
public class Quiz {

	@Id
	@Column(name="ID",updatable=false)
	private int id;
	
	@Column(name="USERID")
	private int userid;
	
	@Column(name="NAME")
	private String name;

	public Quiz() {
		super();
	}

	public Quiz(int id, int userid, String name) {
		super();
		this.id = id;
		this.userid = userid;
		this.name = name;
	}

	public Quiz(int userid, String name) {
		super();
		this.userid = userid;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Quiz [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
}
