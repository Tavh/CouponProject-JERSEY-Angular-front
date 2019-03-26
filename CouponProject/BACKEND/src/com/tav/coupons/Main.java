package com.tav.coupons;
import java.util.Date; 

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tav.coupons.api.AuthenticationApi;
import com.tav.coupons.beans.*;
import com.tav.coupons.enums.CouponType;
import com.tav.coupons.enums.ErrorType;
import com.tav.coupons.exceptions.ApplicationException;
import com.tav.coupons.logic.CompanyController;
import com.tav.coupons.logic.CouponController;
import com.tav.coupons.logic.CustomerController;
import com.tav.coupons.threads.CouponTestThread;
import com.tav.coupons.threads.ExpiredCouponCleanup;
import com.tav.coupons.utilities.DateUtils;
import com.tav.coupons.utilities.InputValidationUtils;
import com.tav.coupons.utilities.*;

public class Main {

	public static void main(String[] args) throws Throwable {

		CouponController couponController = new CouponController();
		CustomerController customerController = new CustomerController();
		
		
		//couponController.purchaseCoupon( customerController.getCustomer(13), couponController.getCoupon(44));
		
	}	
	
}
