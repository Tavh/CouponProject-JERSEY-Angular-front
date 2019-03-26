package com.tav.coupons.dao;
import java.util.List;

import com.tav.coupons.beans.Coupon;
import com.tav.coupons.enums.CouponType;

//This interface has no use except for acting as a method list for the dao
public interface ICouponDao {
	// ------------------------------Creates a new coupon-----------------------------
		public long createCoupon (Coupon coupon) throws Throwable;
		
	// -------------------------------------------------------------------------------
		
	// ------------------------------Removes a coupon---------------------------------
		
		public void removeCoupon (long couponId) throws Throwable;
		
		// Not sure if to keep both or only use one, depends on the program's outcome
		
	// -------------------------------------------------------------------------------

	// ------------------------------Updates a coupon---------------------------------

		public void updateCoupon (Coupon coupon) throws Throwable;
		
		// Not sure if to keep both or only use one, depends on the program's outcome

	// -------------------------------------------------------------------------------

	// -------------------------------------Getters-----------------------------------
		
		public Coupon getCoupon (long couponId) throws Throwable;
		
		public Coupon getCoupon (String couponTitle) throws Throwable;

		public List<Coupon> getAllCoupons() throws Throwable;
		
		public List <Coupon> getCouponsByType (CouponType couponType) throws Throwable;

	// -------------------------------------------------------------------------------
		
	// -------------------Checks if a coupon is recorded in the database--------------
		
		public boolean isCouponExists(long id) throws Throwable;
		
		public boolean isCouponExists(String title) throws Throwable;

		List<Coupon> getPurchasedCoupons(long customerId) throws Throwable;

		List<Coupon> getAllCouponsByCompany(String companyName) throws Throwable;

		void purchaseCoupon(long customerId, long couponId) throws Exception;

		void deleteExpiredCoupons(String date) throws Throwable;
		
	// -------------------------------------------------------------------------------
}
