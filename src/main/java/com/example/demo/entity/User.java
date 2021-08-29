package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "user_name",nullable = false, columnDefinition = "TEXT")
	private String userName;

	@Column(name = "password", nullable = false, columnDefinition = "TEXT")
	private String password;

	@Column(name = "email", unique = true,nullable = false, columnDefinition = "TEXT",length = 30)
	private String email;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String email) {
		this.userName = username;
		this.password = password;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + userName + ", password=" + password + ", email=" + email + "]";
	}

}
