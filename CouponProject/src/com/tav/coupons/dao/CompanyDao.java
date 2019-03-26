package com.tav.coupons.dao;
import com.tav.coupons.beans.*; 
import com.tav.coupons.enums.*;
import com.tav.coupons.exceptions.ApplicationException;
import com.tav.coupons.utilities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao implements ICompanyDao {

// ------------------------------------Constructor------------------------------------------
	public CompanyDao () {
	}

// -----------------------------------------------------------------------------------------

// ---------------------------------------Create a new company-----------------------------

	@Override
	public long createCompany(Company company) throws ApplicationException {
		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			//Establish a connection from the connection manager
			connection = JDBCUtils.getConnection();

			//Creating the SQL query
			//id(company) is defined as a primary key and auto incremented
			String sqlStatement = "INSERT INTO companies (company_name, password, email) VALUES(?,?,?)";

			//Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1,company.getCompanyName());
			preparedStatement.setString(2,company.getPassword());
			preparedStatement.setString(3,company.getEmail());

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
			throw new ApplicationException(ErrorType.DB_ERROR, "Failed to created a new company " + company, e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
		
		
	}

// -----------------------------------------------------------------------------------------

// ------------------------------remove a company------------------

	@Override
	public void removeCompany(long companyId) throws ApplicationException {
		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			//Establish a connection from the connection manager
			connection = JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement = "DELETE FROM companies WHERE company_id = ?";

			//Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, companyId);

			//Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to remove company number " + companyId , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}

	// ----------------------------Update a company------------------------------------

	@Override
	public void updateCompany (Company company) throws ApplicationException {
		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			//Establish a connection from the connection manager
			connection = JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement = "UPDATE companies SET company_name = ?, password = ?, email = ? WHERE company_id = ?";

			//Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1,company.getCompanyName());
			preparedStatement.setString(2,company.getPassword());
			preparedStatement.setString(3,company.getEmail());
			preparedStatement.setLong(4,company.getId());

			//Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to update company " + company.getCompanyName() , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}

	// Not sure if to keep both or only use one, depends on the program's outcome

// ------------------------------------------------------------------------------------------

// -------------------------------------Getters----------------------------------------------

	// Gets a specific company by id
	@Override
	public Company getCompany (long companyId) throws ApplicationException {

		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		Company company = new Company();

		try {
			//Establish a connection from the connection manager
			connection=JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement="SELECT * FROM companies WHERE company_id = ?";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, companyId);

			//Executing the query and saving the DB response in the resultSet.
			result=preparedStatement.executeQuery();

			if (!result.next()) {
				return null;
			}
			
			company = ObjectExctractionUtils.extractCompanyFromResultSet(result);
			
			return company;

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to get company number " + companyId , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}

	// Gets a company by name
	@Override
	public Company getCompany (String companyName) throws ApplicationException {

		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		Company company = new Company();

		try {
			//Establish a connection from the connection manager
			connection=JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement="SELECT * FROM companies WHERE company_name = ?";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, companyName);

			//Executing the query and saving the DB response in the resultSet.
			result=preparedStatement.executeQuery();

			if (!result.next()) {
				return null;
			}
			
			company = ObjectExctractionUtils.extractCompanyFromResultSet(result);
	
			return company;

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to get company " + companyName , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
	
	// Gets all the companies that exist in the database
	@Override
	public List<Company> getAllCompanies() throws ApplicationException {

		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		List <Company> allCompanies = new ArrayList<>();

		try {
			//Establish a connection from the connection manager
			connection=JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement="SELECT * FROM companies";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);

			//Executing the query and saving the DB response in the resultSet.
			result=preparedStatement.executeQuery();
			
			while (result.next()) {
				allCompanies.add(ObjectExctractionUtils.extractCompanyFromResultSet(result));
			}
			
			return allCompanies;

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to get all companies" , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}

// ------------------------------------------------------------------------------------------

// -----------------Checks if a company is recorded in the database--------------------------

	
	// Checks if a company exists in the database by it's name
	@Override
	public boolean isCompanyExists (String companyName) throws ApplicationException {

		//Turn on the connections
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;

		try {
			//Establish a connection from the connection manager
			connection=JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement="SELECT * FROM companies WHERE company_name = ?";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, companyName);

			//Executing the query and saving the DB response in the resultSet.
			result=preparedStatement.executeQuery();

			if (!result.next()) {
				return false;
			}

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to check if company " + companyName + " exists" , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
	
	// Checks if a company exists in the database by it's id
	@Override
	public boolean isCompanyExists (long companyId) throws ApplicationException {

		//Turn on the connections
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;

		try {
			//Establish a connection from the connection manager
			connection=JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement="SELECT * FROM companies WHERE company_id = ?";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, companyId);

			//Executing the query and saving the DB response in the resultSet.
			result=preparedStatement.executeQuery();

			if (!result.next()) {
				return false;
			}

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to check if company number " + companyId + " exists" , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
	
// ------------------------------------------------------------------------------------------
}
