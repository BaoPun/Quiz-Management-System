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
@Table(name="Users")
public class User {


	@Id
	@SequenceGenerator(name = "user_id_generator", allocationSize = 1)
	@GeneratedValue(generator = "user_id_generator", strategy = GenerationType.SEQUENCE)
	@Column(name = "userId", updatable = false)
	private int id;
	
	@Column(name="username")
	private String username;

	@Column(name="passwordhash")
	private String passwordHash;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role")
	private UserType role;
	
	@ManyToOne(optional = true)
	@JoinColumn(name="teacher", nullable = true)
	private User teacher;
	
	
	public User() {}

	public User(String username, String passwordHash, UserType role) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}


	public User(String username, String passwordHash, UserType role, User teacher) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
		this.teacher = teacher;
	}



	public User(int id, String username, String passwordHash, UserType role) {
		this.id = id;
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}



	public User(int id, String username, String passwordHash, UserType role, User teacher) {
		super();
		this.id = id;
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
		this.teacher = teacher;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public UserType getRole() {
		return role;
	}

	public void setRole(UserType role) {
		this.role = role;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacherId(User teacher) {
		this.teacher = teacher;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((passwordHash == null) ? 0 : passwordHash.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (passwordHash == null) {
			if (other.passwordHash != null)
				return false;
		} else if (!passwordHash.equals(other.passwordHash))
			return false;
		if (role != other.role)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + /*", passwordHash=" + passwordHash + */", role=" + role
				+ ", teacher=" + (teacher==null?null:teacher.getId()) + "]";
	}
	
}
