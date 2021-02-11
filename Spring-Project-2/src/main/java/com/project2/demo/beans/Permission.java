package com.project2.demo.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="permissions")
public class Permission {

	@Id
	@SequenceGenerator(name = "permission_id_generator", allocationSize = 1)
	@GeneratedValue(generator = "permission_id_generator", strategy = GenerationType.SEQUENCE)
	@Column(name = "permissionId", updatable = false)
	private int id;
	
	@ManyToOne							
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "quizId")
	private Quiz quiz;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "relationship")
	private PermissionType relationship;

	public Permission() {}

	public Permission(User user, Quiz quiz, PermissionType relationship) {
		super();
		this.user = user;
		this.quiz = quiz;
		this.relationship = relationship;
	}

	public Permission(int id, User user, Quiz quiz, PermissionType relationship) {
		super();
		this.id = id;
		this.user = user;
		this.quiz = quiz;
		this.relationship = relationship;
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

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public PermissionType getRelationship() {
		return relationship;
	}

	public void setRelationship(PermissionType relationship) {
		this.relationship = relationship;
	}
	
	
	
	
}
