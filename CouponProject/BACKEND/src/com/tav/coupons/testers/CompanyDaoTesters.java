package com.tav.coupons.testers;

import static org.junit.Assert.assertEquals; 

import org.junit.Test;

import com.tav.coupons.beans.Company;
import com.tav.coupons.dao.CompanyDao;

public class CompanyDaoTesters {

// --------------------------------Test createCompany---------------------------------------
	
	CompanyDao companyDao = new CompanyDao();
	// Creates a company, fetches it and checks if the retrieved company has the same id
	@Test
	public void testCreateCompany() throws Throwable {
		
		
		Company company = new Company("Test-Company", "123456", "test@email.com");
		long id = companyDao.createCompany(company);
		Company retrievedCompany = companyDao.getCompany(id);
		assertEquals(id, retrievedCompany.getId());
		
		// **** Removing the company is crucial!! ****
		companyDao.removeCompany(id);
		
	}
	
// --------------------------------Test removeCompany---------------------------------------
	
	// Creates a company, attempts to remove it, and checks if it returns null when you try to fetch it
	@Test
	public void testRemoveCompany() throws Throwable {
		
		
		Company company = new Company("Test-Company", "123456", "test@email.com");
		long id = companyDao.createCompany(company);
		companyDao.removeCompany(id);
		Company retrievedCompany = companyDao.getCompany(id);
		assertEquals(null, retrievedCompany);
		
	}
	
	
// --------------------------------Test updateCompany---------------------------------------
	
	/* Creates a company, fetches it in order to update, updates it's name, and then checks if the name returned
	is the same as the name you updated into */
	
	/* Note : The updateCompany function takes in an object parameter, 
	this is the purpose of "CompanyToInjectForUpdate" */
	@Test
	public void testUpdateCompany () throws Throwable {
		
		
		Company company = new Company("Test-Company", "123456", "test@email.com");
		long id = companyDao.createCompany(company);
		Company retrievedCompany = companyDao.getCompany(id);
		Company companyToInjectForUpdate = new Company(retrievedCompany.getId(), "UpdatedCompanyTest", 
				retrievedCompany.getPassword(), retrievedCompany.getEmail());
		companyDao.updateCompany(companyToInjectForUpdate);
		retrievedCompany = companyDao.getCompany(id);
		assertEquals("UpdatedCompanyTest", retrievedCompany.getCompanyName());

		// **** Removing the company is crucial!! ****
		companyDao.removeCompany(id);

		
	}
	
// --------------------------------Test getCompany---------------------------------------
	
	// Creates a company, then fetches it to check if it's id fits the auto-generated id
	// This function is the same as testCreateCompany********
	@Test
	public void testGetCompanyById () throws Throwable {
		
		
		Company company = new Company("Test-Company", "123456", "test@email.com");
		long id = companyDao.createCompany(company);
		Company retrievedCompany = companyDao.getCompany(id);
		assertEquals(id, retrievedCompany.getId());
		
		// **** Removing the company is crucial!! ****
		companyDao.removeCompany(id);
		
	}
	
	// Creates a company, then fetches it to check if it's name fits the name in the "company" object
	@Test
	public void testGetCompanyByName () throws Throwable {
		
		
		Company company = new Company("Test-Company", "123456", "test@email.com");
		long id = companyDao.createCompany(company);
		Company retrievedCompany = companyDao.getCompany(id);
		assertEquals(company.getCompanyName(), retrievedCompany.getCompanyName());
		
		// **** Removing the company is crucial!! ****
		companyDao.removeCompany(id);
	}
	
	// Merely checks if the getAllCompanies functions throws any exceptions...
	@Test
	public void testGetAllCompanies () throws Throwable {
		
		companyDao.getAllCompanies();
	}
	
// --------------------------------Test isCompanyExists---------------------------------------

	@Test
	public void testIsCompanyExistsById () throws Throwable {
		
		
		Company company = new Company("Test-Company", "123456", "test@email.com");
		long id = companyDao.createCompany(company);
		boolean isCompanyExists = companyDao.isCompanyExists(id);
		assertEquals(isCompanyExists, true);
		
		// **** Removing the company is crucial!! ****
		companyDao.removeCompany(id);
	}
	
	@Test
	public void testIsCompanyExistsByName () throws Throwable {
		
		
		Company company = new Company("Test-Company", "123456", "test@email.com");
		long id = companyDao.createCompany(company);
		boolean isCompanyExists = companyDao.isCompanyExists(company.getCompanyName());
		assertEquals(isCompanyExists, true);
		
		// **** Removing the company is crucial!! ****
		companyDao.removeCompany(id);
	}
	
}
