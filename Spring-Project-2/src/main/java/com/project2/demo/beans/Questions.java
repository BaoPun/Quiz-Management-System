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
	@GeneratedValue(generator = "QUESTION_SEQ", strategy = GenerationType.SEQUENCE)
	private int q_id;
	
	@ManyToOne
	private int quizid;
	
	private String q_type;
	
	private String q_desc;

	public Questions() {
		super();
	}

	public Questions(int q_id, int quizid, String q_type, String q_desc) {
		super();
		this.q_id = q_id;
		this.quizid = quizid;
		this.q_type = q_type;
		this.q_desc = q_desc;
	}

	public Questions(int quizid, String q_type, String q_desc) {
		super();
		this.quizid = quizid;
		this.q_type = q_type;
		this.q_desc = q_desc;
	}

	public int getQ_id() {
		return q_id;
	}

	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}

	public int getQuizid() {
		return quizid;
	}

	public void setQuizid(int quizid) {
		this.quizid = quizid;
	}

	public String getQ_type() {
		return q_type;
	}

	public void setQ_type(String q_type) {
		this.q_type = q_type;
	}

	public String getQ_desc() {
		return q_desc;
	}

	public void setQ_desc(String q_desc) {
		this.q_desc = q_desc;
	}

	@Override
	public String toString() {
		return "Questions [q_id=" + q_id + ", quizid=" + quizid + ", q_type=" + q_type + ", q_desc=" + q_desc + "]";
	}
	
	
	
}
