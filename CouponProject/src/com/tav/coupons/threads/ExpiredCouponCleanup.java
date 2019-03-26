package com.tav.coupons.threads;

import java.util.Calendar; 
import java.util.Timer; 
import java.util.TimerTask;
import com.tav.coupons.logic.CouponController;

public class ExpiredCouponCleanup {

	// 86,400 is the amount of seconds in a single day, multiplied by 1000 because the system works in milliseconds
	final static int singleDayInMilliseconds = 86400 * 1000;
	
	public ExpiredCouponCleanup () {
		Timer timer = new Timer();
		
		//Creates a new TimerTask to be executed regularly
		TimerTask regularCouponCleanup = new TimerTask() {
			
			@Override
			public void run () {
				CouponController couponController = new CouponController();
				
				try {
					couponController.deleteExpiredCoupons();
					System.out.println("All expired coupons have been removed from the database");
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		};
		
		// Getting the hour that the thread should run at in date format
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 00);
		today.set(Calendar.MINUTE, 00);
		today.set(Calendar.SECOND, 00);
		
		// How long should the thread wait until it executes the cleanup again
		long period = singleDayInMilliseconds;
		
		timer.schedule(regularCouponCleanup, today.getTime(), period);
	}
}
