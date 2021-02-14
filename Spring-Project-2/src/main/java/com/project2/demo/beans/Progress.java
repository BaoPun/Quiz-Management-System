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

import org.springframework.beans.factory.annotation.Autowired;


@Entity
@Table(name="progress")
public class Progress {
	
	
	@Id
	@SequenceGenerator(name = "progress_id_generator", allocationSize = 1)
	@GeneratedValue(generator = "progress_id_generator", strategy = GenerationType.SEQUENCE)
	@Column(name = "progressId", updatable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	
	@ManyToOne
	@JoinColumn(name = "answerId")
	private Answer answer;

	public Progress() {}
	
	public Progress(int id, User user, Answer answer) {
		this.id = id;
		this.user = user;
		this.answer = answer;
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

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Progress [id=" + id + ", user=" + user + ", answer=" + answer + "]";
	}
	
	
	
	
}
