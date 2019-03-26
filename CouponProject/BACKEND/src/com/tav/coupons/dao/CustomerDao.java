package com.tav.coupons.dao; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tav.coupons.beans.*;
import com.tav.coupons.enums.ErrorType;
import com.tav.coupons.exceptions.ApplicationException;
import com.tav.coupons.utilities.JDBCUtils;
import com.tav.coupons.utilities.ObjectExctractionUtils;

public class CustomerDao implements ICustomerDao {
	
// -----------------------------Constructor------------------------------------
	
	public CustomerDao () {
	}
	
// -----------------------------Create a new customer-------------------------
	
	@Override
	public long createCustomer (Customer customer) throws ApplicationException {
		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			//Establish a connection from the connection manager
			connection = JDBCUtils.getConnection();

			//Creating the SQL query
			//id(customer) is defined as a primary key and auto incremented
			String sqlStatement = "INSERT INTO customers (customer_name, email, password) VALUES(?,?,?)";

			//Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1,customer.getCustomerName());
			preparedStatement.setString(2,customer.getEmail());
			preparedStatement.setString(3,customer.getPassword());

			//Executing the update
			preparedStatement.executeUpdate();
			
			// Fetches the id
			ResultSet generatedKeysResult = preparedStatement.getGeneratedKeys();
			
			if (!generatedKeysResult.next()) {
				throw new ApplicationException(ErrorType.DB_ERROR, "Failed to retrieve an auto-generated id from the database, please check if the id is set on Auto-increment");
			}
			
			return generatedKeysResult.getLong(1);

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR, "Failed to created a new customer " + customer, e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
	
// ----------------------------------------------------------------------------
	
// ----------------------------Remove a customer------------------------------
	
	@Override
	public void removeCustomer (long customerId) throws ApplicationException{
		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			//Establish a connection from the connection manager
			connection = JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement = "DELETE FROM customers WHERE customer_id = ?";

			//Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, customerId);

			//Executing the update
			preparedStatement.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to remove customer number " + customerId , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
	
// ----------------------------------------------------------------------------
	
// -------------------------------Update a customer---------------------------
	
	// Updates a customer by "Overriding" it with the new version of the customer
	@Override
	public void updateCustomer (Customer customer) throws ApplicationException {
		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			//Establish a connection from the connection manager
			connection = JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement = "UPDATE customers SET customer_name = ?, email = ?, password = ? WHERE customer_id = ?";

			//Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1,customer.getCustomerName());
			preparedStatement.setString(2,customer.getEmail());
			preparedStatement.setString(3,customer.getPassword());
			preparedStatement.setLong(4,customer.getId());

			//Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to update company " + customer.getCustomerName() , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
	
	
// ----------------------------------------------------------------------------
	
// --------------------------------Getters-------------------------------------
	
	// Gets a specific customer by email
	@Override
	public Customer getCustomer (String customerEmail) throws ApplicationException {
		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		Customer customer = new Customer();

		try {
			//Establish a connection from the connection manager
			connection=JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement="SELECT * FROM customers WHERE email = ?";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, customerEmail);

			//Executing the query and saving the DB response in the resultSet.
			result=preparedStatement.executeQuery();
			
			if (!result.next()) {
				return null;
			}
			
			customer = ObjectExctractionUtils.extractCustomerFromResultSet(result);
			
			System.out.println(customer);

			return customer;

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to get customer by email " + customerEmail , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
	
	// Gets a specific customer by id
	@Override
	public Customer getCustomer (long customerId) throws ApplicationException {
		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		Customer customer = new Customer();

		try {
			//Establish a connection from the connection manager
			connection=JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement="SELECT * FROM customers WHERE customer_id = ?";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, customerId);

			//Executing the query and saving the DB response in the resultSet.
			result=preparedStatement.executeQuery();

			if (!result.next()) {
				return null;
			}
			
			customer = ObjectExctractionUtils.extractCustomerFromResultSet(result);

			return customer;

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to get customer number " + customerId , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
	
	// Gets all the customers that are recorded in the database
	@Override
	public List<Customer> getAllCustomers () throws ApplicationException {
		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		List <Customer> allCustomers = new ArrayList<>();

		try {
			//Establish a connection from the connection manager
			connection=JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement="SELECT * FROM customers";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);

			//Executing the query and saving the DB response in the resultSet.
			result=preparedStatement.executeQuery();
			
			while (result.next()) {
				allCustomers.add(ObjectExctractionUtils.extractCustomerFromResultSet(result));
			}

			return allCustomers;

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to get all customers" , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
	


// -----------------------------------------------------------------------------

// ---------------Check if a customer is recorded in the database--------------
	
	//Checks if a customer exists by id
	@Override
	public boolean isCustomerExists(long customerId) throws ApplicationException {

		//Turn on the connections
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;

		try {
			//Establish a connection from the connection manager
			connection=JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement="SELECT * FROM customers WHERE customer_id = ?";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, customerId);

			//Executing the query and saving the DB response in the resultSet.
			result=preparedStatement.executeQuery();

			if (!result.next()) {
				return false;
			}

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to check if customer number " + customerId + " exists" , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
	
	//Checks if a customer exists by email
	@Override
	public boolean isCustomerExists(String customerEmail) throws ApplicationException {

		//Turn on the connections
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;

		try {
			//Establish a connection from the connection manager
			connection=JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement="SELECT * FROM customers WHERE email = ?";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, customerEmail);

			//Executing the query and saving the DB response in the resultSet.
			result=preparedStatement.executeQuery();

			if (!result.next()) {
				return false;
			}

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to check if the customer with the email " + customerEmail + " exists" , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
// -----------------------------------------------------------------------------
}
