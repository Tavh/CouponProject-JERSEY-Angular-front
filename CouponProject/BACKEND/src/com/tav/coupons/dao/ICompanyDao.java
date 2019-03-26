package com.tav.coupons.dao;
import java.util.List; 
import com.tav.coupons.beans.Company;

//This interface has no use except for acting as a method list for the dao
public interface ICompanyDao {

	// -----------------------------------------------------------------------------------------
	
	// ---------------------------------------Creates a new company-----------------------------
		
		public long createCompany (Company company) throws Throwable;
		
	// -----------------------------------------------------------------------------------------
		
	// ------------------------------Functions that remove an existing company------------------
		
		// Removes a company by it's name
		public void removeCompany (long id) throws Throwable;
		
		// Not sure if to keep both or only use one, depends on the program's outcome
		
	// ----------------------------Updates a specific company------------------------------------
		
		// Updates a company profile by company
		public void updateCompany (Company company) throws Throwable;
		
		// Not sure if to keep both or only use one, depends on the program's outcome
		
	// ------------------------------------------------------------------------------------------
		
	// -------------------------------------Getters----------------------------------------------
		
		public Company getCompany (long companyId) throws Throwable;
		
		public Company getCompany (String companyName) throws Throwable;
		
		public List<Company> getAllCompanies() throws Throwable;
		
	// ------------------------------------------------------------------------------------------
		
	// -----------------Checks if a company is recorded in the database--------------------------
		
		// Checks if a company exists by it's name
		public boolean isCompanyExists (String companyName) throws Throwable;
		
		// Checks if a company exists by it's id
		public boolean isCompanyExists(long id) throws Throwable;
		
	// ------------------------------------------------------------------------------------------
}
