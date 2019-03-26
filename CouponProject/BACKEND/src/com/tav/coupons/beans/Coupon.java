package com.tav.coupons.beans;

import javax.xml.bind.annotation.XmlRootElement;

import com.tav.coupons.enums.CouponType;

@XmlRootElement
public class Coupon {
	private long id;
	private String title;
	private String motherCompany;
	private String startDate;
	private String endDate;
	private int amount;
	private CouponType couponType;
	// Image is a string for the time being
	private String message;
	private float price;
	private String image;


//-----------------------------Constructors---------------------------------------
	
	public Coupon (){		
	}

	public Coupon(String title, String motherCompany, String startDate, String endDate, int amount, CouponType type, String message,
			float price, String image) {
		this.title = title;
		this.motherCompany = motherCompany;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.couponType = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}

	public Coupon(long id, String title, String motherCompany, String startDate, String endDate, int amount, CouponType type, String message,
			float price, String image) {
		this.id = id;
		this.title = title;
		this.motherCompany = motherCompany;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.couponType = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}

//-----------------------------Getters and Setters---------------------------------------

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getMotherCompany () {
		return motherCompany;
	}
	
	public void setMotherCompany (String motherCompany) {
		this.motherCompany = motherCompany;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public CouponType getType() {
		return couponType;
	}
	public void setType(CouponType type) {
		this.couponType = type;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", motherCompany=" + motherCompany + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", couponType=" + couponType + ", message=" + message + ", price=" + price
				+ ", image=" + image + "]";
	}


}
