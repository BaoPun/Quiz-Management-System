package com.project2.demo.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="answers")
public class Answer {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "answertext")
	private String answerText;
	
	// Change type to Question later
	@Column(name = "questionid")
	private int questionId;
	
	
	@Column(name = "iscorrect")
	private int isCorrect;
	
	
	@Column(name = "ordering")
	private int ordering;

	
	

	public Answer() {
		super();
		this.id = -1;
		this.answerText = "";
		this.questionId = -1;
		this.isCorrect = -1;
		this.ordering = -1;
	}


	public Answer(int id, String answerText, int questionId, int isCorrect, int ordering) {
		super();
		this.id = id;
		this.answerText = answerText;
		this.questionId = questionId;
		this.isCorrect = isCorrect;
		this.ordering = ordering;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the answerText
	 */
	public String getAnswerText() {
		return answerText;
	}


	/**
	 * @param answerText the answerText to set
	 */
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}


	/**
	 * @return the questionId
	 */
	public int getQuestionId() {
		return questionId;
	}


	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}


	/**
	 * @return the isCorrect
	 */
	public int getIsCorrect() {
		return isCorrect;
	}


	/**
	 * @param isCorrect the isCorrect to set
	 */
	public void setIsCorrect(int isCorrect) {
		this.isCorrect = isCorrect;
	}


	/**
	 * @return the ordering
	 */
	public int getOrdering() {
		return ordering;
	}


	/**
	 * @param ordering the ordering to set
	 */
	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}


	@Override
	public String toString() {
		return "Answer [id=" + id + ", answerText=" + answerText + ", questionId=" + questionId + ", isCorrect="
				+ isCorrect + ", ordering=" + ordering + "]";
	}
	
	
	
	
	
	
	
}
