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
@Table(name="answers")
public class Answer implements Comparable<Answer> {


	
	@Id
	@SequenceGenerator(name = "answer_id_generator", allocationSize = 1)
	@GeneratedValue(generator = "answer_id_generator", strategy = GenerationType.SEQUENCE)
	@Column(name = "answerId", updatable = false)
	private int id;
	
	@Column(length = 255, name = "answertext")
	private String answerText;
	
	@ManyToOne							// a question contains multiple possible answers
										// and a specific answer belongs to one question
	@JoinColumn(name = "questionId")
	private Question question;
	
	
	@Column(name = "iscorrect")
	private boolean isCorrect;
	
	
	@Column(name = "ordering")
	private int ordering;

	
	

	public Answer() {}


	public Answer(int id, Question question, String answerText, boolean isCorrect, int ordering) {
		this.id = id;
		this.question = question;
		this.answerText = answerText;
		this.isCorrect = isCorrect;
		this.ordering = ordering;
	}
	
	

	public Answer(Question question, String answerText, boolean isCorrect, int ordering) {
		this.question = question;
		this.answerText = answerText;
		this.isCorrect = isCorrect;
		this.ordering = ordering;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getAnswerText() {
		return answerText;
	}


	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}


	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}


	public boolean getIsCorrect() {
		return isCorrect;
	}


	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}


	public int getOrdering() {
		return ordering;
	}


	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}


	@Override
	public String toString() {
		return "Answer [id=" + id + ", answerText=" + answerText + ", question=" + question + ", isCorrect=" + isCorrect
				+ ", ordering=" + ordering + "]";
	}


	@Override
	public int compareTo(Answer o) {
		if (this.id < o.id) {
			return -1;
		} else if (this.id > o.id) {
			return 1;
		} else {
			if (this.ordering < o.ordering) {
				return -1;
			} else if (this.ordering > o.ordering) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	
	
}
