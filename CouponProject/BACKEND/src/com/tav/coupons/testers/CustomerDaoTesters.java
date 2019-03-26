package com.tav.coupons.testers;

import static org.junit.Assert.assertEquals; 

import org.junit.Test;

import com.tav.coupons.beans.Customer;
import com.tav.coupons.dao.CustomerDao;

public class CustomerDaoTesters {

// --------------------------------Test createCustomer---------------------------------------

	CustomerDao customerDao = new CustomerDao();
	// Creates a customer, fetches it and checks if the retrieved customer has the same id
	@Test
	public void testCreateAndGetCustomer() throws Throwable {


		Customer customer = new Customer("Test-Customer Levi", "testlevi@email.com", "123456");
		long id = customerDao.createCustomer(customer);
		Customer retrievedCustomer = customerDao.getCustomer(id);
		assertEquals(id, retrievedCustomer.getId());

		// **** Removing the customer is crucial!! ****
		customerDao.removeCustomer(id);

	}

// --------------------------------Test removeCustomer---------------------------------------

	// Creates a customer, attempts to remove it, and checks if it returns null when you try to fetch it
	@Test
	public void testRemoveCustomer() throws Throwable {


		Customer customer = new Customer("Test-Customer Levi", "testlevi@email.com", "123456");
		long id = customerDao.createCustomer(customer);
		customerDao.removeCustomer(id);
		Customer retrievedCustomer = customerDao.getCustomer(id);
		assertEquals(null, retrievedCustomer);

	}

// --------------------------------Test updateCustomer---------------------------------------

	/* Creates a customer, fetches it in order to update, updates it's name, and then checks if the name returned
		is the same as the name you updated into */

	/* Note : The updateCustomer function takes in an object parameter, 
		this is the purpose of "CustomerToInjectForUpdate" */

	@Test
	public void testUpdateCustomer () throws Throwable {


		Customer customer = new Customer("Test-Customer Levi", "testlevi@email.com", "123456");
		long id = customerDao.createCustomer(customer);
		Customer retrievedCustomer = customerDao.getCustomer(id);
		Customer customerToInjectForUpdate = new Customer(retrievedCustomer.getId(), "Test-customer Menachem", 
				retrievedCustomer.getEmail(), retrievedCustomer.getPassword());
		customerDao.updateCustomer(customerToInjectForUpdate);
		retrievedCustomer = customerDao.getCustomer(id);
		assertEquals("Test-customer Menachem", retrievedCustomer.getCustomerName());

		// **** Removing the customer is crucial!! ****
		customerDao.removeCustomer(id);

	}

// --------------------------------Test getCustomer---------------------------------------

	// Creates a customer, then fetches it to check if it's id fits the auto-generated id
	// This function is the same as testCreateCustomer********
	@Test
	public void testGetCustomerById () throws Throwable {


		Customer customer = new Customer("Test-Customer Levi", "testlevi@email.com", "123456");
		long id = customerDao.createCustomer(customer);
		Customer retrievedCustomer = customerDao.getCustomer(id);
		assertEquals(id, retrievedCustomer.getId());

		// **** Removing the customer is crucial!! ****
		customerDao.removeCustomer(id);

	}

	// Creates a customer, then fetches it to check if it's name fits the name in the "customer" object
	@Test
	public void testGetCustomerByEmail () throws Throwable {


		Customer customer = new Customer("Test-Customer Levi", "testlevi@email.com", "123456");
		long id = customerDao.createCustomer(customer);
		Customer retrievedCustomer = customerDao.getCustomer(id);
		assertEquals(customer.getEmail(), retrievedCustomer.getEmail());

		// **** Removing the customer is crucial!! ****
		customerDao.removeCustomer(id);

	}
	
	// Merely tests if the getAllCustomers function throws an exception
	@Test
	public void testGetAllCustomers () throws Throwable {
		

		customerDao.getAllCustomers();
	}
	
// --------------------------------Test isCustomerExists---------------------------------------

	@Test
	public void testIsCustomerExistsById () throws Throwable {
			

		Customer customer = new Customer("Test-Customer Levi", "testlevi@email.com", "123456");
		long id = customerDao.createCustomer(customer);
		boolean isCustomerExists = customerDao.isCustomerExists(id);
		assertEquals(isCustomerExists, true);
			
		// **** Removing the customer is crucial!! ****
		customerDao.removeCustomer(id);
	}
		
		
	public void testIsCustomerExistsByEmail () throws Throwable {
			
		Customer customer = new Customer("Test-Customer Levi", "testlevi@email.com", "123456");
		long id = customerDao.createCustomer(customer);
		boolean isCustomerExists = customerDao.isCustomerExists("testlevi@email.coms");
		assertEquals(isCustomerExists, true);
			
		// **** Removing the customer is crucial!! ****
		customerDao.removeCustomer(id);
	}


}
