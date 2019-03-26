package com.tav.coupons.testers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.tav.coupons.beans.Company;
import com.tav.coupons.dao.CompanyDao;
import com.tav.coupons.logic.CompanyController;

@RunWith(MockitoJUnitRunner.class)

public class CompanyControllerTesters {
	
	@Mock
	private CompanyDao companyDao;
	
	// Company is not created when ran with Mockito
	@InjectMocks
	private CompanyController companyController = new CompanyController();
	
	@Test
	public void testCreateCompany () throws Throwable {
		
		Company company = new Company("Test-Company", "123456", "test@email.com");
		long id = companyController.createCompany(company);
	}

}
