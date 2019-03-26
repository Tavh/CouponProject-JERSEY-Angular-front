package com.tav.coupons.threads;
import com.tav.coupons.beans.Coupon; 
import com.tav.coupons.logic.CouponController;

public class CouponTestThread extends Thread {

	private int milisecondsToHalt;
	private Coupon coupon;
	
	public CouponTestThread (int milisecondsToHalt, Coupon coupon) {
		this.milisecondsToHalt = milisecondsToHalt;
		this.coupon = coupon;
	}
	
	public void run () {
		try {
			Thread.sleep(milisecondsToHalt);
			CouponController coupCont = new CouponController ();
			coupCont.createCoupon(coupon);
			System.out.println("Creating a new coupon :" + coupon.getTitle());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
