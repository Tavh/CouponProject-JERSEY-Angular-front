package com.tav.coupons.logic;

// This interface makes sure that a class that implements it also inherits the "login" method
public interface IUser {
	
	public boolean authenticate(String companyName, String password) throws Throwable;
}
