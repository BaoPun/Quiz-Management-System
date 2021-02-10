package com.project2.demo.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="questions")
public class Questions {

	@Id
	@Column(updatable=false)
	@GeneratedValue
	private int id;
	
	private int quizid;
	
	private String questionType;
	
	private String description;

	public Questions() {
		super();
	}

	public Questions(int q_id, int quizid, String q_type, String q_desc) {
		super();
		this.id = q_id;
		this.quizid = quizid;
		this.questionType = q_type;
		this.description = q_desc;
	}

	public Questions(int quizid, String q_type, String q_desc) {
		super();
		this.quizid = quizid;
		this.questionType = q_type;
		this.description = q_desc;
	}

	public int getQ_id() {
		return id;
	}

	public void setQ_id(int q_id) {
		this.id = q_id;
	}

	public int getQuizid() {
		return quizid;
	}

	public void setQuizid(int quizid) {
		this.quizid = quizid;
	}

	public String getQ_type() {
		return questionType;
	}

	public void setQ_type(String q_type) {
		this.questionType = q_type;
	}

	public String getQ_desc() {
		return description;
	}

	public void setQ_desc(String q_desc) {
		this.description = q_desc;
	}

	@Override
	public String toString() {
		return "Questions [q_id=" + id + ", quizid=" + quizid + ", q_type=" + questionType + ", q_desc=" + description + "]";
	}
	
	
	
}
