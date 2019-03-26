package com.tav.coupons.api;

import java.util.List; 

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import com.tav.coupons.beans.Customer;
import com.tav.coupons.beans.UserDetails;
import com.tav.coupons.exceptions.ApplicationException;
import com.tav.coupons.logic.CustomerController;

@Path("/loggedin/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerApi {

	CustomerController customerController;

	// -----------------------------------Constructor-----------------------------------------

	public CustomerApi () {
		customerController = new CustomerController();
	}

	// ---------------------------------Creates a new customer--------------------------------

	@POST
	@Path("/unsecured")
	public void createCustomer (Customer customer) throws ApplicationException {
		customerController.createCustomer(customer);

	}

	// ----------------------------------Removes a customer------------------------------------

	@DELETE
	@Path("/{customerId}")
	public void removeCustomer (@PathParam("customerId")long customerId) throws ApplicationException {
		customerController.removeCustomer(customerId);
	}

	// -------------------------------Updates a customer's details-----------------------------

	@PUT
	public void updateCustomer (UserDetails userDetails, @Context HttpServletRequest request, 
			@Context HttpServletResponse response) throws ApplicationException {
		
		// Storing the cookies and passing them to the controller for processing
		Cookie[] cookies = request.getCookies();
		
		customerController.updateCustomer(userDetails, cookies);
	}

	// ----------------------------------------Getters-----------------------------------------

	// Gets a specific customer by id
	@GET
	@Path("/{customerId}")
	public Customer getCustomer (@PathParam("customerId") long customerId) throws ApplicationException {

		return customerController.getCustomer(customerId);
	}

	// Gets a specific customer by email
	@GET
	@Path("/unsecured/bycustomeremail")
	public Customer getCustomer (@QueryParam("customerEmail") String customerEmail) throws ApplicationException {
		
		return customerController.getCustomer(customerEmail);
	}

	// Gets all the customers that exist in the database
	@GET
	@Path("/allcustomers")
	public List<Customer> getAllCustomers () throws ApplicationException {
		
		// This has no use in the client side right now, so it's pointless to censor the passwords for now.
		return customerController.getAllCustomers();
	}


	// -----------------------------------------------------------------------------------------

}
