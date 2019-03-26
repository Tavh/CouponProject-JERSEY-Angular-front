import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Coupon } from '../models/coupon-object.model';
import { CouponTransaction } from '../models/coupon-transaction.model';
import { CouponArrayWrapper } from '../models/wrappers/coupon-array-wrapper.model';

@Injectable({
  providedIn: 'root'
})
export class CouponsService {

// ----------------------------------------------- Constructor ---------------------------------------------------

  constructor(private myHttpClient: HttpClient) {
   }

// ----------------------------------------- Gets all the coupons in the db --------------------------------------

   public getAllCoupons() {

    return this.myHttpClient.get<CouponArrayWrapper>(`http://localhost:8080/CouponProject/rest/coupons/allcoupons`)
   }

// ----------------------------------------- Gets a coupon by title --------------------------------------

  public getCouponByTitle(title:string) {
      
    return this.myHttpClient.get<Coupon>(`http://localhost:8080/CouponProject/rest/coupons/bytitle?couponTitle=${title}`);
  }
// --------------------------------- Gets all the coupons of a certain type -------------------------------

    public getCouponsByType (couponType:string) {
        return this.myHttpClient.get<CouponArrayWrapper>(`http://localhost:8080/CouponProject/rest/coupons/bytype?couponType=${couponType}`);
    }

// --------------------------------- Purchases a coupon -------------------------------

    public purchaseCoupon (couponTransaction:CouponTransaction) {

        // Setting an observable object for use in the component
        return this.myHttpClient.post<void>("http://localhost:8080/CouponProject/rest/coupons/purchasecoupon", couponTransaction, {observe: 'response', withCredentials : true});
    }
// --------------------------------- Gets coupons purchased by a specific customer -------------------------------

    public getPurchasedCoupons (customerId:number) {
        // Setting an observable object for use in the component
        return this.myHttpClient.get<CouponArrayWrapper>(`http://localhost:8080/CouponProject/rest/coupons/purchasedcoupons?customerId=${customerId}`);
    }

        
// -------------------------- Gets all the coupons that were made by a certain company ---------------------------
   
    public getCouponsByCompany(companyName:string) {
        // Setting an observable object for use in the component
        return this.myHttpClient.get<CouponArrayWrapper>(`http://localhost:8080/CouponProject/rest/coupons/companycoupons?companyName=${companyName}`);
    }

// -------------------------- Create new coupon ---------------------------


    public createCoupon (coupon:Coupon) {

        // Setting an observable object for use in the component
        return this.myHttpClient.post<void>("http://localhost:8080/CouponProject/rest/coupons", coupon, {observe: 'response', withCredentials : true});
    }
}



