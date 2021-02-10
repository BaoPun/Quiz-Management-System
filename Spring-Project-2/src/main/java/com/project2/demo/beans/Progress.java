package com.project2.demo.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="progress")
public class Progress
{
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "userid")
	private int userId;
	
	@Column(name = "answerid")
	private int answerId;

	public Progress() {}
	
	public Progress(int id, int userId, int answerId) {
		super();
		this.id = id;
		this.userId = userId;
		this.answerId = answerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	
	
}
