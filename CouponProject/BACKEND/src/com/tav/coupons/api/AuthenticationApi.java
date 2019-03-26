package com.tav.coupons.api;

import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.tav.coupons.beans.Company;
import com.tav.coupons.beans.Customer;
import com.tav.coupons.beans.UserDetails;
import com.tav.coupons.exceptions.ApplicationException;
import com.tav.coupons.logic.CompanyController;
import com.tav.coupons.logic.CustomerController;

@Path("/authenticate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthenticationApi {

// ------------------------------------------------------Checks if user details are true--------------------------------------------

	@POST
	@Path("/login")
	public UserDetails login (@Context HttpServletRequest request, 
			@Context HttpServletResponse response, 
			UserDetails loginData)  throws ApplicationException {

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ In case it's a customer ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		if (loginData.getUserType().equals("customer")) {

			String email = loginData.getUsername();
			String password = loginData.getPassword();
			
			System.out.println(email);
			
			CustomerController customerController = new CustomerController();
			boolean isUserLegitimate = customerController.authenticate(email, password);

			if (isUserLegitimate) {

				request.getSession();
				Cookie cookie = new Cookie("customerEmail", loginData.getUsername());
				cookie.setPath("/");
				response.addCookie(cookie);

				HttpServletResponse res = (HttpServletResponse) response;
				res.setHeader("LoginStatus", "Customer : " + email + ", has logged in successfully");

				/* Getting the customer details and inserting them into an object that will contain info needed
				   in the client side */
				Customer customer = customerController.getCustomer(email);
				UserDetails userDetails = new UserDetails();
				userDetails.setId(customer.getId());
				userDetails.setUsername(customer.getCustomerName());
				userDetails.setEmail(customer.getEmail());
				userDetails.setLoginStatus("OK");
				userDetails.setUserType("customer");

				return userDetails;
			}
		} 
		
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ In case it's a company ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		else if (loginData.getUserType().equals("company")) {
			

			String username = loginData.getUsername();
			String password = loginData.getPassword();
			
			CompanyController companyController = new CompanyController();

			boolean isUserLegitimate = companyController.authenticate(username, password);


			if (isUserLegitimate) {

				request.getSession();
				Cookie cookie = new Cookie("companyName", loginData.getUsername());
				cookie.setPath("/");
				response.addCookie(cookie);

				HttpServletResponse res = (HttpServletResponse) response;
				res.setHeader("LoginStatus", "Company : " + username + ", has logged in successfully");

				/* Getting the company details and inserting them into an object that will contain info needed
				   in the client side */
				Company company = companyController.getCompany(username);
				UserDetails userDetails = new UserDetails();
				userDetails.setId(company.getId());
				userDetails.setUsername(company.getCompanyName());
				userDetails.setEmail(company.getEmail());
				userDetails.setLoginStatus("OK");
				userDetails.setUserType("company");

				return userDetails;
			}
		}
		
		// This is to satisfy the function, the function should never get here
		return new UserDetails();
	}
	
	
	
// ------------------------------------------------------Checks if user password is true--------------------------------------------

	@POST
	@Path("/authenticatepassword")
	public void authenticatePassword (@Context HttpServletRequest request, 
			@Context HttpServletResponse response, 
			UserDetails loginData) throws ApplicationException {

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ In case it's a customer ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		if (loginData.getUserType().equals("customer")) {
			
			String email = loginData.getEmail();
			String password = loginData.getPassword();
			
			CustomerController customerController = new CustomerController();
			customerController.authenticate(email, password);
		}
		
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ In case it's a company ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		else if (loginData.getUserType().equals("company")) {
			
			
			String username = loginData.getUsername();
			String password = loginData.getPassword();
			
			CompanyController companyController = new CompanyController();
			companyController.authenticate(username, password);
		}
		
		// Function shouldn't get so far, but if it does, it returns a falls
		
	}
	
}
