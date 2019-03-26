import { Component, OnInit } from '@angular/core';
import { UserDetailsWrapper } from '../shared/models/wrappers/user-details-wrapper.model';
import { CouponsService } from '../shared/services/coupons.service';
import { AuthenticationService } from '../shared/services/authentication.service';
import { Coupon } from '../shared/models/coupon-object.model';

@Component({
  selector: 'app-purchased-coupons',
  templateUrl: './purchased-coupons.component.html',
  styleUrls: ['./purchased-coupons.component.css']
})
export class PurchasedCouponsComponent implements OnInit {

// ------------------------------------- Class variables -------------------------------------------

  sessionUserDetailsWrapper:UserDetailsWrapper;
  coupons:Coupon[];

// ------------------------------------ Constructor ---------------------------------------------
  
  constructor(private myCouponsService:CouponsService, private myAuthenticationService:AuthenticationService) {
    this.sessionUserDetailsWrapper = myAuthenticationService.sessionUserDetailsWrapper;
    this.getPurchasedCoupons();
  }

// --------------------------------- Gets the coupons purchased by a customer  ---------------------------------------

  getPurchasedCoupons () {
    let customerId = this.sessionUserDetailsWrapper.getId();

    const observable =  this.myCouponsService.getPurchasedCoupons(customerId);
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

  ngOnInit() {
  }

}
