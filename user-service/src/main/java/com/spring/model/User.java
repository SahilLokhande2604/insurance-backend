package com.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "userDB")
public class User {
	@Id
	private String username;
	private String password;
	private String role;
	private int userId;
	private String name;
	private String phone;
    
	// Getters and setters
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public String getRole() { return role; }
	public void setRole(String role) { this.role = role; }

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User() {
		// Default constructor
	}

	public User(String username, String password, String role, String name, String phone) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.name = name;
		this.phone = phone;
	}
	}
    

