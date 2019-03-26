package com.tav.coupons.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDetails {

	private long id;
	private String username;
	private String password;
	private String email;
	private String loginStatus;
	
	// Customer, Company
	private String userType;
	//-----------------------------Constructor---------------------------------------



	public UserDetails () {
	}

	//-----------------------------Getters and Setters------------------------------
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "UserDetails [username=" + username + ", password=" + password + ", id=" + id + ", email=" + email
				+ ", loginStatus=" + loginStatus + "]";
	}
}
