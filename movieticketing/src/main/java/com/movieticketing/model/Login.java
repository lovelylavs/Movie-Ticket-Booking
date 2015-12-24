package com.movieticketing.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "login", catalog = "movie_ticketing", uniqueConstraints = {
        @UniqueConstraint(columnNames = "userid") })
public class Login implements Serializable {

	private String userId;
	private String password;
	private String role;

    @Id
    @Column(name = "userid", unique = true, nullable = false)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userName) {
		this.userId = userName;
	}

    @Column(name = "password", unique = true, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    @Column(name = "role", unique = true, nullable = false)
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Login{" +
				"userName='" + userId + '\'' +
				", password='" + password + '\'' +
				", role='" + role + '\'' +
				'}';
	}
}
