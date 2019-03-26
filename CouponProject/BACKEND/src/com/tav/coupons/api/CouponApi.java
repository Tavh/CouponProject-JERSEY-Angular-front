package com.tav.coupons.api;


import java.util.List;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE; 
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.tav.coupons.beans.Coupon;
import com.tav.coupons.beans.CouponTransaction;
import com.tav.coupons.enums.CouponType;
import com.tav.coupons.exceptions.ApplicationException;
import com.tav.coupons.logic.CouponController;

@Path("/coupons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CouponApi {
	
	CouponController couponController;

// ------------------------------------Constructor---------------------------------
	
	public CouponApi () {

		this.couponController = new CouponController();
	}

// -----------------------------Creates a new coupon--------------------------------------
		
	@POST
		public void createCoupon (Coupon coupon) throws ApplicationException {
		
		System.out.println(coupon.getType());

		CouponType couponTypeEnum = CouponType.valueOf(coupon.getType().toString());
		coupon.setType(couponTypeEnum);
		
		couponController.createCoupon(coupon);
	}
// ----------------------------------------Removes a coupon----------------------------------

	@DELETE
	@Path("/{couponId}")
	public void removeCoupon (@PathParam("couponId") long couponId) throws ApplicationException{

		couponController.removeCoupon(couponId);
	}

// ---------------------------------------Updates a coupon-------------------------------
		
	@PUT
	public void updateCoupon (Coupon coupon) throws ApplicationException {

		couponController.updateCoupon(coupon);
	}

//--------------------------------------Gets a coupon-----------------------------------------

	// Gets a specific coupon by it's id
	@GET
	@Path("/{couponId}")
	public Coupon getCoupon (@PathParam("couponId") long couponId) throws ApplicationException {
		return couponController.getCoupon(couponId);
	}
		
	// Gets a specific coupon by it's title
	@GET
	@Path("/bytitle")
	public Coupon getCoupon (@QueryParam("couponTitle") String couponTitle) throws ApplicationException {
		return couponController.getCoupon(couponTitle);
	}
		
	// Gets all the coupons that exist in the database
	@GET
	@Path("/allcoupons")
	public List <Coupon> getAllCoupons () throws ApplicationException {
		
		return couponController.getAllCoupons();
	}
		
	// Gets a coupon by a specific CouponType (ENUM)
	@GET
	@Path("/bytype")
	public List <Coupon> getCouponsByType (@QueryParam("couponType") String couponType) throws ApplicationException {
			
		CouponType couponTypeEnum = CouponType.valueOf(couponType);
		return couponController.getCouponsByType(couponTypeEnum);
	}
		
	// Gets all coupons that a specific customer purchased
	@GET
	@Path("/purchasedcoupons")
	public List <Coupon> getCouponsPurchasedByCustomer (@QueryParam("customerId") long customerId) throws ApplicationException {
			
		return couponController.getCouponsPurchasedByCustomer(customerId);
	}
		
	// Gets all the coupons that were made by a specific company
	@GET
	@Path("/companycoupons")
	public List<Coupon> getAllCouponsByCompany (@QueryParam("companyName") String companyName) throws ApplicationException {
			
		return couponController.getAllCouponsByCompany(companyName);
	}
		
// ----------------------------------------Purchases a coupon----------------------------------

	// Uses a customer and a coupon in order to make a purchase
	@POST
	@Path("/purchasecoupon")
	public void purchaseCoupon (CouponTransaction couponTransaction) throws ApplicationException {
		
		couponController.purchaseCoupon(couponTransaction.getCustomerId(), couponTransaction.getCouponId());
	}

// -----------------------------------------------------------------------------------------
}
