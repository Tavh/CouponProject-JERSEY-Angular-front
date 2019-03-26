import { Component, OnInit } from '@angular/core';
import { UserDetailsWrapper } from '../shared/models/wrappers/user-details-wrapper.model';
import { AuthenticationService } from '../shared/services/authentication.service';
import { Coupon } from '../shared/models/coupon-object.model';
import { CouponsService } from '../shared/services/coupons.service';

@Component({
  selector: 'app-create-coupon',
  templateUrl: './create-coupon.component.html',
  styleUrls: ['./create-coupon.component.css']
})
export class CreateCouponComponent implements OnInit {
  
// ------------------------------------- Class variables -------------------------------------------

  sessionUserDetailsWrapper:UserDetailsWrapper;
  title:string;
  expirationDate:string;
  amount:number;
  type:string;
  couponText:string;
  price:number;
  imgURL:string;

// ------------------------------------ Constructor ---------------------------------------------

  constructor(private myAuthenticationService:AuthenticationService, private myCouponsService:CouponsService) {
       this.sessionUserDetailsWrapper = myAuthenticationService.sessionUserDetailsWrapper;
   }

// -------------------------------- Creates a coupon ----------------------------------------
  createCoupon () {
    // ~~~~~~~~~~~~~~~~~~~~~~~~ Object creation ~~~~~~~~~~~~~~~~~~~~~~~~
    let coupon = new Coupon();

    coupon.title = this.title;
    coupon.motherCompany = this.sessionUserDetailsWrapper.getUsername();
    coupon.startDate = this.formatDate(new Date());
    coupon.endDate = this.formatDate(new Date(this.expirationDate));
    coupon.amount = this.amount;
    coupon.type = this.type;
    coupon.message = this.couponText;
    coupon.price = this.price;
    if (this.imgURL == undefined) {
      coupon.image = "None";
    } else {
      coupon.image = this.imgURL;
    }

  // ~~~~~~~~~~~~~~~~~~~~~~~ Sending http request ~~~~~~~~~~~~~~~~~~~~~~~~~~

  const observable =  this.myCouponsService.createCoupon(coupon);
  observable.subscribe(
    res => {
      alert(coupon.title + " was created successfully!")
      location.reload();
  },
  err=>{
    alert("Something went wrong during coupon creation..")
    console.log(err)
    location.reload();
  }
) 
}

// ------------------------------ Converts dates into dd/mm/yyyy ------------------------------------

formatDate (date:Date) {
  let dd1 = date.getDate();
  let mm1 = date.getMonth() + 1; //Months are zero based
  let yyyy1 = date.getFullYear();

  let mm1String;
  let dd1String;

  if(mm1 < 10) {
     mm1String = "0" + mm1; 
   } else {
    mm1String = mm1;
  }

   if(dd1 < 10) {
     dd1String = "0" + dd1; 
  } else {
     dd1String = dd1;
   }

   return dd1String + "/" + mm1String + "/" + yyyy1;
}

  ngOnInit() {
  }

}
