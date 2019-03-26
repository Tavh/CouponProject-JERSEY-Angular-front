import { Component, OnInit } from '@angular/core';
import { CouponsService } from '../shared/services/coupons.service';
import { Coupon } from '../shared/models/coupon-object.model';
import { UserDetailsWrapper } from '../shared/models/wrappers/user-details-wrapper.model';
import { AuthenticationService } from '../shared/services/authentication.service';
import { CouponTransaction } from '../shared/models/coupon-transaction.model';

@Component({
  selector: 'app-browse-coupons',
  templateUrl: './browse-coupons.component.html',
  styleUrls: ['./browse-coupons.component.css']
})
export class BrowseCouponsComponent implements OnInit {

// ------------------------------------- Class variables -------------------------------------------

  typeSelection:string;
  title:string;
  coupons: Coupon[];
  sessionUserDetailsWrapper:UserDetailsWrapper;
  today:Date;

// ------------------------------------ Constructor ---------------------------------------------

  constructor(private myCouponsService:CouponsService, private myAuthenticationService:AuthenticationService) {
    this.sessionUserDetailsWrapper = myAuthenticationService.sessionUserDetailsWrapper;
    this.getAllCoupons();

    // Setting the date as today
    this.today = new Date();
  }

// ------------------------------------ Gets a single coupon ---------------------------------------------

  getCoupon() {
    const observable = this.myCouponsService.getCouponByTitle(this.title);
    observable.subscribe(
      res => {
          this.coupons = [
              res
          ];
      },
      err=>{console.log(err)}
  )
  }

// ------------------------------------ Gets all coupons ---------------------------------------------

  getAllCoupons() {
    const observable = this.myCouponsService.getAllCoupons();
    observable.subscribe(
      res => {
          // Deals with array returned as undefined, only one element, and multiple elements
          if (res == undefined) {
              this.coupons = [];
          } else if (!Array.isArray(res.coupon)) {
              this.coupons = [
                  res.coupon
              ];
          } else {
              this.coupons = res.coupon;
          }
      },
      err=>{console.log(err)}
  )
  }

// ------------------------------------ Gets all coupons of a certain type ---------------------------------------------

  getCouponsByType() {
    const observable = this.myCouponsService.getCouponsByType(this.typeSelection);
    observable.subscribe(
      res => {
          // Deals with array returned as undefined, only one element, and multiple elements
          if (res == undefined) {
              this.coupons = [];
          } else if (!Array.isArray(res.coupon)) {
              this.coupons = [
                  res.coupon
              ];
          } else {
              this.coupons = res.coupon;
          }
         
      },
      err=>{console.log(err)}
    )
  }

// ------------------------------------ Compares between dates ---------------------------------------------

// This is used in order compare dates that are strings
  compareDates (date:string):Boolean {

    if (new Date(date) < this.today) {
      return true;
    }

    return false;
  }

// ------------------------------------ Makes a coupon purchase ---------------------------------------------

  purchaseCoupon(coupon:Coupon) {

    let couponTransaction = new CouponTransaction(this.sessionUserDetailsWrapper.getId(), coupon.id);

    console.log(couponTransaction);

    const observable = this.myCouponsService.purchaseCoupon(couponTransaction);
    observable.subscribe(
     response => {  
          
         if (response.ok) {
           console.log(couponTransaction);
           this.myCouponsService.getAllCoupons();
           alert('"' + coupon.title + '"' + " was purchased successfully");
         }

       },
       err=>{
        alert("Coupon purchase failed");
        console.log(err)
      }
  )
  }
  
   ngOnInit() {
   }

}
