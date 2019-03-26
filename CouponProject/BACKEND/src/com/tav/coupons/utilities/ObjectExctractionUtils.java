package com.tav.coupons.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tav.coupons.beans.Company;
import com.tav.coupons.beans.Coupon;
import com.tav.coupons.beans.Customer;
import com.tav.coupons.enums.CouponType;

public class ObjectExctractionUtils {

	//-----------------------------Makes a company out of database info---------------------------------------
	
	public static Company extractCompanyFromResultSet(ResultSet result) throws SQLException {
		
		Company company = new Company();

		company.setId(result.getLong("company_id"));
		company.setCompanyName(result.getString("company_name"));
		company.setPassword(result.getString("password"));
		company.setEmail(result.getString("email"));		
		
		return company;
	}
	
//-----------------------------Makes a coupon out of database info---------------------------------------
	
	public static Coupon extractCouponFromResultSet(ResultSet result) throws SQLException {
		
		Coupon coupon = new Coupon();

		coupon.setId(result.getLong("coupon_id"));
		coupon.setTitle(result.getString("title"));
		coupon.setMotherCompany(result.getString("mother_company"));
		coupon.setStartDate(result.getString("start_date"));
		coupon.setEndDate(result.getString("end_date"));	
		coupon.setAmount(result.getInt("amount"));
		
		// Translating the enum into a string in order to catch it
		CouponType couponTypeEnum = CouponType.valueOf(result.getString("coupon_type"));
		coupon.setType(couponTypeEnum);
		
		coupon.setMessage(result.getString("message"));
		coupon.setPrice(result.getFloat("price"));
		coupon.setImage(result.getString("image"));
		
		return coupon;
	}
	
	
	public static Customer extractCustomerFromResultSet(ResultSet result) throws SQLException {
		
		Customer customer = new Customer();

		customer.setId(result.getLong("customer_id"));
		customer.setCustomerName(result.getString("customer_name"));
		customer.setEmail(result.getString("email"));
		customer.setPassword(result.getString("password"));	
		
		return customer;
	}
}
