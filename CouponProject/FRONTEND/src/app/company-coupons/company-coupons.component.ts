import { Component, OnInit } from '@angular/core';
import { UserDetailsWrapper } from '../shared/models/wrappers/user-details-wrapper.model';
import { AuthenticationService } from '../shared/services/authentication.service';
import { CouponsService } from '../shared/services/coupons.service';
import { Coupon } from '../shared/models/coupon-object.model';

@Component({
  selector: 'app-company-coupons',
  templateUrl: './company-coupons.component.html',
  styleUrls: ['./company-coupons.component.css']
})
export class CompanyCouponsComponent implements OnInit {

// ------------------------------ Class variables ---------------------------------------

  sessionUserDetailsWrapper:UserDetailsWrapper;
  coupons:Coupon[];

// ------------------------------ Constructor ---------------------------------------

  constructor(private myAuthenticationService:AuthenticationService, private myCouponsService:CouponsService) {
      this.sessionUserDetailsWrapper = myAuthenticationService.sessionUserDetailsWrapper;
      this.getCompanyCoupons();
   }

// ------------------------------ Gets a specific company's coupons ---------------------------------------

   getCompanyCoupons () {
    let companyName = this.sessionUserDetailsWrapper.getUsername();

    const observable =  this.myCouponsService.getCouponsByCompany(companyName);
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
