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

import com.tav.coupons.beans.Company;
import com.tav.coupons.beans.UserDetails;
import com.tav.coupons.exceptions.ApplicationException;
import com.tav.coupons.logic.CompanyController;

@Path("/loggedin/companies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompanyApi {

	CompanyController companyController;

// ------------------------------------Constructor-------------------------------------

	public CompanyApi () {
		this.companyController = new CompanyController();
	}

// ---------------------------------Creates a company------------------------------------

	@POST
	@Path("/unsecured")
	public void createCompany (Company company) throws ApplicationException {
		
		companyController.createCompany(company);
	}

// -------------------------------------------------------------------------------
// ------------------------------Remove a company---------------------------------

	// Removes a company by id

	@DELETE
	@Path("/{companyId}")
	public void removeCompany(@PathParam("companyId") long companyId) throws ApplicationException {
		System.out.println(companyId);
		companyController.removeCompany(companyId);
	}

// ------------------------------------------------------------------------------------
// ----------------------Update a company----------------------------------------------

	@PUT
	public void updateCompany (UserDetails userDetails, @Context HttpServletRequest request, 
			@Context HttpServletResponse response) throws ApplicationException {

		// Storing the cookies and passing them to the controller for processing
		Cookie[] cookies = request.getCookies();
		
		companyController.updateCompany(userDetails, cookies);
	}

//-----------------------------------------------------------------------------------
// ---------------------------------------Getters-------------------------------------
	
	// Gets a specific company by id 
	@GET
	@Path("/{companyId}")
	public Company getCompany (@PathParam("companyId") long companyId) throws ApplicationException {

		return companyController.getCompany(companyId);
	}

	// Gets a specific company by name
	@GET
	@Path("/unsecured/bycompanyname")
	public Company getCompany (@QueryParam("companyName") String companyName) throws ApplicationException {

		return companyController.getCompany(companyName);
	}

	// Gets all the companies that exist in the database
	@GET
	@Path("/allcompanies")
	public List<Company> getAllCompanies () throws ApplicationException {

		// This has no use in the client side right now, so it's pointless to censor the passwords for now.
		return companyController.getAllCompanies();

	}

// -------------------------------------------------------------------------------------------


}
