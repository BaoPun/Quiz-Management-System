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
public class Question implements Comparable<Question> {


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
	private String type;
	
	@Column(name = "description")
	private String description;
	
	@Column(name="ordering")
	private int ordering;

	public Question() {
		super();
	}

	public Question(int id, Quiz quiz, String type, String description) {
		this.id = id;
		this.quiz = quiz;
		this.type = type;
		this.description = description;
	}

	public Question(Quiz quiz, String type, String description) {
		this.quiz = quiz;
		this.type = type;
		this.description = description;
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
		return type;
	}

	/**
	 * @param questionType the questionType to set
	 */
	public void setQuestionType(String type) {
		this.type = type;
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
		return "Question [id=" + id + ", quiz=" + quiz + ", type=" + type + ", description=" + description + "]";
	}

	@Override
	public int compareTo(Question o) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getOrdering() {
		return ordering;
	}

	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}

	
	
	
	
}
