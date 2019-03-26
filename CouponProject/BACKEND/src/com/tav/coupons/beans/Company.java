package com.tav.coupons.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Company {

	
	private long id;
	private String companyName;
	private String password;
	private String email;
	
//-----------------------------Constructors---------------------------------------

	public Company() {
	}

	public Company(String companyName, String password, String email) {
		this.companyName = companyName;
		this.password = password;
		this.email = email;
	}

	public Company(long id, String companyName, String password, String email) {
		this.id = id;
		this.companyName = companyName;
		this.password = password;
		this.email = email;
	}



//-----------------------------Getters and Setters---------------------------------------

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
		return "Company [id=" + id + ", companyName=" + companyName + ", password=" + password + ", email=" + email
				+ "]";
	}
}
