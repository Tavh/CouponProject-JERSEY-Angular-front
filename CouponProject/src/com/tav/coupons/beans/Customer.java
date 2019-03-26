package com.tav.coupons.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {

	private long id;
	private String customerName;
	private String email;
	private String password;

//-----------------------------Constructors---------------------------------------

	public Customer() {
		
	}


	public Customer(String customerName, String email, String password) {
		this.customerName = customerName;
		this.email = email;
		this.password = password;
	}
	
	public Customer(long id, String customerName, String email, String password) {
		this.id = id;
		this.customerName = customerName;
		this.email = email;
		this.password = password;
	}


//-----------------------------Getters and Setters---------------------------------------

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
		return "Customer [id=" + id + ",  customerName=" + customerName + ", email=" + email + ", password=" + password
				+ "]";
	}


}
