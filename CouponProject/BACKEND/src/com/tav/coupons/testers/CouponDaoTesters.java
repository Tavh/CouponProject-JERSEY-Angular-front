package com.tav.coupons.testers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.tav.coupons.beans.Company;
import com.tav.coupons.beans.Coupon;
import com.tav.coupons.beans.Customer;
import com.tav.coupons.dao.CompanyDao;
import com.tav.coupons.dao.CouponDao;
import com.tav.coupons.dao.CustomerDao;
import com.tav.coupons.enums.CouponType;

public class CouponDaoTesters {

	// --------------------------------Test createCoupon---------------------------------------

	CouponDao couponDao = new CouponDao();
	CustomerDao customerDao = new CustomerDao();
	CompanyDao companyDao = new CompanyDao();
	// Creates a coupon, fetches it and checks if the retrieved coupon has the same id
	@Test
	public void testCreateCoupon() throws Throwable {

		Coupon coupon = new Coupon("Test Coupon", "Hentai Hub", "01/01/2000", "01/01/2010", 
				100, CouponType.GIFT, "Test message", 99.99f, null);
		long id = couponDao.createCoupon(coupon);
		Coupon retrievedCoupon = couponDao.getCoupon(id);
		assertEquals(id, retrievedCoupon.getId());

		// **** Removing the coupon is crucial!! ****
		couponDao.removeCoupon(id);

	}

	// --------------------------------Test removeCoupon---------------------------------------

	// Creates a coupon, attempts to remove it, and checks if it returns null when you try to fetch it
	@Test
	public void testRemoveCoupon() throws Throwable {

		Coupon coupon = new Coupon("Test Coupon", "Hentai Hub", "01/01/2000", "01/01/2010", 
				100, CouponType.GIFT, "Test message", 99.99f, null);
		long id = couponDao.createCoupon(coupon);
		couponDao.removeCoupon(id);
		Coupon retrievedCoupon = couponDao.getCoupon(id);
		assertEquals(null, retrievedCoupon);

	}

	// --------------------------------Test updateCoupon---------------------------------------

	/* Creates a coupon, fetches it in order to update, updates it's name, and then checks if the name returned
		is the same as the name you updated into */

	/* Note : The updateCoupon function takes in an object parameter, 
		this is the purpose of "CouponToInjectForUpdate" */
	@Test
	public void testUpdateCoupon () throws Throwable {

		Coupon coupon = new Coupon("Test Coupon", "Hentai Hub", "01/01/2000", "01/01/2010", 
				100, CouponType.GIFT, "Test message", 99.99f, null);
		long id = couponDao.createCoupon(coupon);
		Coupon retrievedCoupon = couponDao.getCoupon(id);
		Coupon CouponToInjectForUpdate = new Coupon(retrievedCoupon.getId(), retrievedCoupon.getTitle(), 
				retrievedCoupon.getMotherCompany(),	retrievedCoupon.getStartDate(), retrievedCoupon.getEndDate(), 
				retrievedCoupon.getAmount(), retrievedCoupon.getType(), "It is updated", retrievedCoupon.getPrice(),
				retrievedCoupon.getImage());
		couponDao.updateCoupon(CouponToInjectForUpdate);
		retrievedCoupon = couponDao.getCoupon(id);
		assertEquals("It is updated", retrievedCoupon.getMessage());

		// **** Removing the coupon is crucial!! ****
		couponDao.removeCoupon(id);

	}

	// --------------------------------Test getCoupon---------------------------------------

	// Creates a coupon, then fetches it to check if it's id fits the auto-generated id
	// This function is the same as testCreateCoupon********
	@Test
	public void testGetCouponById () throws Throwable {

		Coupon coupon = new Coupon("Test Coupon", "Hentai Hub", "01/01/2000", "01/01/2010", 
				100, CouponType.GIFT, "Test message", 99.99f, null);
		long id = couponDao.createCoupon(coupon);
		Coupon retrievedCoupon = couponDao.getCoupon(id);
		assertEquals(id, retrievedCoupon.getId());

		// **** Removing the coupon is crucial!! ****
		couponDao.removeCoupon(id);

	}

	// Creates a coupon, then fetches it to check if it's id fits the auto-generated id
	// This function is the same as testCreateCoupon********
	@Test
	public void testGetCouponByTitle () throws Throwable {

		Coupon coupon = new Coupon("Test Coupon", "Hentai Hub", "01/01/2000", "01/01/2010", 
				100, CouponType.GIFT, "Test message", 99.99f, null);
		long id = couponDao.createCoupon(coupon);
		Coupon retrievedCoupon = couponDao.getCoupon(id);
		assertEquals("Test Coupon", retrievedCoupon.getTitle());

		// **** Removing the coupon is crucial!! ****
		couponDao.removeCoupon(id);

	}

	@Test
	public void testGetCouponsByType () throws Throwable {

		// Creating a coupon just to make sure that the database isn't empty
		Coupon coupon = new Coupon("Test Coupon", "Hentai Hub", "01/01/2000", "01/01/2010", 
				100, CouponType.GIFT, "Test message", 99.99f, null);
		long id = couponDao.createCoupon(coupon);
		List<Coupon> allGiftCoupons = couponDao.getCouponsByType(CouponType.GIFT);

		for (Coupon giftCoupon : allGiftCoupons) {

			assertEquals(CouponType.GIFT, giftCoupon.getType());
		}

		// **** Removing the coupon is crucial!! ****
		couponDao.removeCoupon(id);
	}

	@Test
	public void testGetAllCoupons () throws Throwable {
		couponDao.getAllCoupons();
	}


	@Test
	public void testGetPurchasedCoupons() throws Throwable {

		// A customer to purchase a coupon
		Customer customer = new Customer("Test-Customer Levi", "testlevi@email.com", "123456");
		long custId = customerDao.createCustomer(customer);
		Customer retrievedCustomer = customerDao.getCustomer(custId);

		// A coupon to be purchased by the customer
		Coupon coupon = new Coupon("Test Coupon", "Hentai Hub", "01/01/2000", "01/01/2010", 
				100, CouponType.GIFT, "Test message", 99.99f, null);
		long coupId = couponDao.createCoupon(coupon);
		Coupon retrievedCoupon = couponDao.getCoupon(coupId);

		// Making the coupon purchase
		couponDao.purchaseCoupon(retrievedCustomer.getId(), retrievedCoupon.getId());

		List <Coupon> purchasedCoupons = couponDao.getPurchasedCoupons(retrievedCustomer.getId());

		for (Coupon purchasedCoupon : purchasedCoupons) {

			assertEquals(retrievedCoupon.getId(), purchasedCoupon.getId());
		}

		// **** Removing the coupon is crucial!! ****
		couponDao.removeCoupon(coupId);

		// **** Removing the customer is crucial!! ****
		customerDao.removeCustomer(custId);
	}

	@Test
	public void testGetAllCouponsByCompany() throws Throwable {

		// A coupon to be purchased by the customer
		Coupon coupon = new Coupon("Test Coupon", "Test-Company", "01/01/2000", "01/01/2010", 
				100, CouponType.GIFT, "Test message", 99.99f, null);
		long coupId = couponDao.createCoupon(coupon);
		Coupon retrievedCoupon = couponDao.getCoupon(coupId);

		Company company = new Company("Test-Company", "123456", "test@email.com");
		long compId = companyDao.createCompany(company);
		Company retrievedCompany = companyDao.getCompany(compId);
		
		List <Coupon> companyCoupons = couponDao.getAllCouponsByCompany(retrievedCompany.getCompanyName());
		
		for (Coupon companyCoupon : companyCoupons) {

			assertEquals(retrievedCoupon.getId(), companyCoupon.getId());
		}
		

		// **** Removing the coupon is crucial!! ****
		couponDao.removeCoupon(coupId);

		// **** Removing the company is crucial!! ****
		companyDao.removeCompany(compId);
	}
	
	
	
	// Works the same as testGetPurchasedCoupons()
	@Test
	public void testPurchaseCoupon() throws Throwable {

		// A customer to purchase a coupon
		Customer customer = new Customer("Test-Customer Levi", "testlevi@email.com", "123456");
		long custId = customerDao.createCustomer(customer);
		Customer retrievedCustomer = customerDao.getCustomer(custId);

		// A coupon to be purchased by the customer
		Coupon coupon = new Coupon("Test Coupon", "Hentai Hub", "01/01/2000", "01/01/2010", 
				100, CouponType.GIFT, "Test message", 99.99f, null);
		long coupId = couponDao.createCoupon(coupon);
		Coupon retrievedCoupon = couponDao.getCoupon(coupId);

		// Making the coupon purchase
		couponDao.purchaseCoupon(retrievedCustomer.getId(), retrievedCoupon.getId());

		List <Coupon> purchasedCoupons = couponDao.getPurchasedCoupons(retrievedCustomer.getId());

		for (Coupon purchasedCoupon : purchasedCoupons) {

			assertEquals(retrievedCoupon.getId(), purchasedCoupon.getId());
		}

		// **** Removing the coupon is crucial!! ****
		couponDao.removeCoupon(coupId);

		// **** Removing the customer is crucial!! ****
		customerDao.removeCustomer(custId);
	}
	
	

	@Test
	public void testIsCouponExistsById () throws Throwable {
		
		
		Coupon coupon = new Coupon("Test Coupon", "Hentai Hub", "01/01/2000", "01/01/2010", 
				100, CouponType.GIFT, "Test message", 99.99f, null);
		long id = couponDao.createCoupon(coupon);
		boolean isCouponExists = couponDao.isCouponExists(id);
		assertEquals(isCouponExists, true);
		
		// **** Removing the coupon is crucial!! ****
		couponDao.removeCoupon(id);
	}
	
	@Test
	public void testIsCouponExistsByTitle () throws Throwable {
		
		Coupon coupon = new Coupon("Test Coupon", "Hentai Hub", "01/01/2000", "01/01/2010", 
				100, CouponType.GIFT, "Test message", 99.99f, null);
		long id = couponDao.createCoupon(coupon);
		boolean isCouponExists = couponDao.isCouponExists(coupon.getTitle());
		assertEquals(isCouponExists, true);
		
		// **** Removing the coupon is crucial!! ****
		couponDao.removeCoupon(id);
	}

}
