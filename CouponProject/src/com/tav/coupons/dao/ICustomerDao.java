package com.tav.coupons.dao;
import java.util.List; 
import com.tav.coupons.beans.Customer;

// This interface has no use except for acting as a method list for the dao
public interface ICustomerDao {
		
	// -----------------------------Creates a new customer-------------------------
		
		public long createCustomer (Customer customer) throws Throwable;
		
	// ----------------------------------------------------------------------------
		
	// ----------------------------Removes a customer------------------------------
		
		public void removeCustomer (long customerId) throws Throwable;
				
	// ----------------------------------------------------------------------------
		
	// -------------------------------Updates a customer---------------------------
		
		public void updateCustomer (Customer customer) throws Throwable;
		
	// ----------------------------------------------------------------------------
		
	// --------------------------------Getters-------------------------------------
		
		public Customer getCustomer (String customerEmail) throws Throwable;
		
		public List<Customer> getAllCustomers () throws Throwable;

	// -----------------------------------------------------------------------------

	// ---------------Checks if a customer is recorded in the database--------------
		
		//Checks if a customer exists by his email
		public boolean isCustomerExists(long customerId) throws Throwable;

		Customer getCustomer(long customerId) throws Throwable;

		boolean isCustomerExists(String customerEmail) throws Throwable;
		
	// -----------------------------------------------------------------------------
}
