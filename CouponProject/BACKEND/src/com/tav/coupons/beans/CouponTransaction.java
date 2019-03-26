package com.tav.coupons.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CouponTransaction {

	private long customerId;
	private long couponId;
	
//-----------------------------Constructor---------------------------------------
	
	public CouponTransaction() {
	}

//-----------------------------Getters and Setters---------------------------------------

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	@Override
	public String toString() {
		return "CouponTransaction [customerId=" + customerId + ", couponId=" + couponId + "]";
	}
}
