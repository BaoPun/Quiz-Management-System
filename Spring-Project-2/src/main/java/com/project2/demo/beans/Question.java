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
@Table(name="questions")
public class Question {

	@Id
	@SequenceGenerator(name = "question_id_generator", allocationSize = 1)
	@GeneratedValue(generator = "question_id_generator", strategy = GenerationType.SEQUENCE)
	@Column(name = "questionId", updatable=false)
	private int id;
	
	@ManyToOne							// A single quiz contains multiple questions
										// while a question belongs to only 1 quiz
	@JoinColumn(name = "quizId")
	private Quiz quiz;
	
	@Column(name = "questiontype")
	private String questionType;
	
	@Column(name = "description")
	private String description;

	public Question() {
		super();
	}

	public Question(int q_id, Quiz quiz, String q_type, String q_desc) {
		super();
		this.id = q_id;
		this.quiz = quiz;
		this.questionType = q_type;
		this.description = q_desc;
	}

	public Question(Quiz quiz, String q_type, String q_desc) {
		super();
		this.quiz = quiz;
		this.questionType = q_type;
		this.description = q_desc;
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
	 * @return the quiz
	 */
	public Quiz getQuiz() {
		return quiz;
	}

	/**
	 * @param quiz the quiz to set
	 */
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	/**
	 * @return the questionType
	 */
	public String getQuestionType() {
		return questionType;
	}

	/**
	 * @param questionType the questionType to set
	 */
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", quiz=" + quiz + ", questionType=" + questionType + ", description="
				+ description + "]";
	}

	
	
	
	
}
