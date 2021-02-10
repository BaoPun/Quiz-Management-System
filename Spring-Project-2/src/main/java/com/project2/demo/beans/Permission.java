package com.project2.demo.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="permissions")
public class Permission {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	private User u_id;
	
	private Quiz q_id;
	
	private PermissionType relationship;

	public Permission() {
		super();
	}

	public Permission(User u_id, Quiz q_id, PermissionType relationship) {
		super();
		this.u_id = u_id;
		this.q_id = q_id;
		this.relationship = relationship;
	}

	public Permission(int id, User u_id, Quiz q_id, PermissionType relationship) {
		super();
		this.id = id;
		this.u_id = u_id;
		this.q_id = q_id;
		this.relationship = relationship;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getU_id() {
		return u_id;
	}

	public void setU_id(User u_id) {
		this.u_id = u_id;
	}

	public Quiz getQ_id() {
		return q_id;
	}

	public void setQ_id(Quiz q_id) {
		this.q_id = q_id;
	}

	public PermissionType getRelationship() {
		return relationship;
	}

	public void setRelationship(PermissionType relationship) {
		this.relationship = relationship;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", u_id=" + u_id + ", q_id=" + q_id + ", relationship=" + relationship + "]";
	}
	
	
	
	
	
	
	
}
