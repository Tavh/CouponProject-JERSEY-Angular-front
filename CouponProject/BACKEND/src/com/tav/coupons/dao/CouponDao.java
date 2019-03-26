
package com.tav.coupons.dao;
import java.sql.Connection;   
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.tav.coupons.beans.*;
import com.tav.coupons.enums.*;
import com.tav.coupons.exceptions.ApplicationException;
import com.tav.coupons.utilities.JDBCUtils;
import com.tav.coupons.utilities.ObjectExctractionUtils;

public class CouponDao implements ICouponDao {

// ------------------------------Create a new coupon-----------------------------

	@Override
	public long createCoupon (Coupon coupon) throws ApplicationException {
		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			//Establish a connection from the connection manager
			connection = JDBCUtils.getConnection();

			//Creating the SQL query
			//CompanyID is defined as a primary key and auto incremented
			String sqlStatement = "INSERT INTO coupons (title, mother_company, start_date, end_date, amount, coupon_type, message, price, image) VALUES(?,?,?,?,?,?,?,?,?)";

			//Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1,coupon.getTitle());
			preparedStatement.setString(2,coupon.getMotherCompany());
			preparedStatement.setString(3,coupon.getStartDate());
			preparedStatement.setString(4,coupon.getEndDate());
			preparedStatement.setInt(5,coupon.getAmount());
			preparedStatement.setString(6,coupon.getType().toString());
			preparedStatement.setString(7,coupon.getMessage());
			preparedStatement.setFloat(8,coupon.getPrice());
			preparedStatement.setString(9,coupon.getImage());

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
			throw new ApplicationException(ErrorType.DB_ERROR, "Failed to created a new coupon " + coupon, e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
	
// -------------------------------------------------------------------------------
	
// ------------------------------Remove a coupon---------------------------------

	@Override
	public void removeCoupon (long couponId) throws ApplicationException {
		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			//Establish a connection from the connection manager
			connection = JDBCUtils.getConnection();

			//Creating the SQL query
			//id(customer) is defined as a primary key and auto incremented
			String sqlStatement = "DELETE FROM coupons WHERE coupon_id = ?";

			//Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, couponId);

			//Executing the update
			preparedStatement.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to remove coupon number " + couponId , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
	
// -------------------------------------------------------------------------------

// ------------------------------Update a coupon---------------------------------
	
	//Updates a coupon by "overriding" it with the new version of the coupon
	@Override
	public void updateCoupon (Coupon coupon) throws ApplicationException {
		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			//Establish a connection from the connection manager
			connection = JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement = "UPDATE coupons SET title = ?, mother_company = ?, start_date = ?, end_date = ?, amount = ?, coupon_type = ?, message = ?, price = ?, image = ? WHERE coupon_id = ?";

			//Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1,coupon.getTitle());
			preparedStatement.setString(2,coupon.getMotherCompany());
			preparedStatement.setString(3,coupon.getStartDate());
			preparedStatement.setString(4,coupon.getEndDate());
			preparedStatement.setInt(5,coupon.getAmount());
			preparedStatement.setString(6,coupon.getType().toString());
			preparedStatement.setString(7,coupon.getMessage());
			preparedStatement.setFloat(8,coupon.getPrice());
			preparedStatement.setString(9,coupon.getImage());
			preparedStatement.setLong(10,coupon.getId());

			//Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to update coupon " + coupon.getTitle() , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}

// -------------------------------------------------------------------------------

// -------------------------------------Getters-----------------------------------
	
	// Gets a specific coupon by it's id
	@Override
	public Coupon getCoupon (long couponId) throws ApplicationException {
		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		Coupon coupon = new Coupon();

		try {
			//Establish a connection from the connection manager
			connection=JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement="SELECT * FROM coupons WHERE coupon_id = ?";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, couponId);

			//Executing the query and saving the DB response in the resultSet.
			result=preparedStatement.executeQuery();

			if (!result.next()) {
				return null;
			}
			
			coupon = ObjectExctractionUtils.extractCouponFromResultSet(result);
			
			return coupon;

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to get coupon number " + couponId , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
	
	// Gets a specific coupon by it's title
	@Override
	public Coupon getCoupon (String couponTitle) throws ApplicationException {
		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		Coupon coupon = new Coupon();

		try {
			//Establish a connection from the connection manager
			connection=JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement="SELECT * FROM coupons WHERE title = ?";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, couponTitle);

			//Executing the query and saving the DB response in the resultSet.
			result=preparedStatement.executeQuery();

			if (!result.next()) {
				return null;
			}
			
			coupon = ObjectExctractionUtils.extractCouponFromResultSet(result);
			
			return coupon;

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to get coupon " + couponTitle , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}

	// Gets all the coupons that exist in the database
	@Override
	public List<Coupon> getAllCoupons() throws ApplicationException {
		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		List <Coupon> allCoupons = new ArrayList<>();

		try {
			//Establish a connection from the connection manager
			connection=JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement="SELECT * FROM coupons";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);

			//Executing the query and saving the DB response in the resultSet.
			result=preparedStatement.executeQuery();
			
			while (result.next()) {
				allCoupons.add(ObjectExctractionUtils.extractCouponFromResultSet(result));
			}
			
			return allCoupons;

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to get all coupons" , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
	
	// Gets all the coupons that correspond to a specific CouponType (ENUM)
	@Override
	public List <Coupon> getCouponsByType (CouponType couponType) throws ApplicationException {
		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		List <Coupon> allCouponsByType = new ArrayList<>();

		try {
			//Establish a connection from the connection manager
			connection=JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement="SELECT * FROM coupons WHERE coupon_type = ?";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);
			
			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, couponType.toString());

			//Executing the query and saving the DB response in the resultSet.
			result=preparedStatement.executeQuery();
			
			while (result.next()) {
				allCouponsByType.add(ObjectExctractionUtils.extractCouponFromResultSet(result));
			} 

			return allCouponsByType;

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to get all coupons of type " + couponType , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
	
	// Gets all the coupons that specific customer has purchased (by a customer's id)
	@Override
	public List<Coupon> getPurchasedCoupons (long customerId) throws ApplicationException {
		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		List <Coupon> allCoupons = new ArrayList<>();

		try {
			//Establish a connection from the connection manager
			connection=JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement="SELECT coupons.* FROM customer_coupon JOIN coupons ON customer_coupon.customer_id = ? AND customer_coupon.coupon_id = coupons.coupon_id";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);
			
			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, customerId);

			//Executing the query and saving the DB response in the resultSet.
			result=preparedStatement.executeQuery();
			
			while (result.next()) {
				allCoupons.add(ObjectExctractionUtils.extractCouponFromResultSet(result));
			}
			
			return allCoupons;

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to get all coupons that were purchased by customer number " + customerId , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
	
	// Gets all the coupons that were made by specific company
	@Override
	public List <Coupon> getAllCouponsByCompany (String companyName) throws ApplicationException {

		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		List <Coupon> allCoupons = new ArrayList<>();

		try {
			//Establish a connection from the connection manager
			connection=JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement="SELECT * FROM coupons WHERE mother_company = ?";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, companyName);

			//Executing the query and saving the DB response in the resultSet.
			result=preparedStatement.executeQuery();
			
			while (result.next()) {
				allCoupons.add(ObjectExctractionUtils.extractCouponFromResultSet(result));
			}

			return allCoupons;

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR, "Company number " + companyName + " has no coupons in the database", e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}

// -------------------------------------------------------------------------------
// ---------------------------Purchase a coupon--------------------------------
	
	@Override
	public void purchaseCoupon (long customerId, long couponId) throws ApplicationException {

		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			//Establish a connection from the connection manager
			connection = JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement = "INSERT INTO customer_coupon (customer_id, coupon_id) VALUES(?,?)";


			//Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, customerId);
			preparedStatement.setLong(2, couponId);

			//Executing the update
			preparedStatement.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR, "Customer number " + customerId + "Failed to purchase coupon number " + couponId , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}

	}
	
// -------------------Checks if a coupon is recorded in the database--------------
	
	// Checks in the database if a coupon exists by id
	@Override
	public boolean isCouponExists(long couponId) throws ApplicationException {

		//Turn on the connections
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;

		try {
			//Establish a connection from the connection manager
			connection=JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement="SELECT * FROM coupons WHERE coupon_id = ?";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, couponId);

			//Executing the query and saving the DB response in the resultSet.
			result=preparedStatement.executeQuery();

			if (!result.next()) {
				return false;
			}

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to check if coupon number " + couponId + " exists" , e);

		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
	
	// Checks in the database if a coupon exists by it's title
	@Override
	public boolean isCouponExists(String couponTitle) throws ApplicationException {

		//Turn on the connections
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;

		try {
			//Establish a connection from the connection manager
			connection=JDBCUtils.getConnection();

			//Creating the SQL query
			String sqlStatement="SELECT * FROM coupons WHERE title = ?";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, couponTitle);

			//Executing the query and saving the DB response in the resultSet.
			result=preparedStatement.executeQuery();

			if (!result.next()) {
				return false;
			}

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to check if coupon " + couponTitle + " exists" , e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
// ---------------------------------- Checks if a coupon is recorded in the database---------------------------
	
	@Override
	public void deleteExpiredCoupons (String date) throws ApplicationException {
		//Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			//Establish a connection from the connection manager
			connection = JDBCUtils.getConnection();

			//Creating the SQL query
			//id(customer) is defined as a primary key and auto incremented
			String sqlStatement = "DELETE FROM coupons WHERE end_date < today";

			//Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, date);

			//Executing the update
			preparedStatement.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(ErrorType.DB_ERROR,"Failed to remove expired coupons", e);
		} 
		finally {
			//Closing the resources
			JDBCUtils.closeResources(connection, preparedStatement);
		}
	}
	

}
