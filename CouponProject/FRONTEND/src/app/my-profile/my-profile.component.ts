import { Component, OnInit } from '@angular/core';
import { UserDetailsWrapper } from '../shared/models/wrappers/user-details-wrapper.model';
import { AuthenticationService } from '../shared/services/authentication.service';
import { CustomersService } from '../shared/services/customers.service';
import { CompaniesService } from '../shared/services/companies.service';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

// ------------------------------------- Class variables -------------------------------------------

  currentPassword:string;
  newPassword:string;
  confirmNewPassword:string;
  isDisplayPasswordWrongError:boolean;
  sessionUserDetailsWrapper:UserDetailsWrapper;

// ------------------------------------ Constructor ---------------------------------------------

  constructor(private myAuthenticationService:AuthenticationService, private myCustomersService:CustomersService, private myCompaniesService:CompaniesService) {
    this.isDisplayPasswordWrongError = false;
    this.sessionUserDetailsWrapper = myAuthenticationService.sessionUserDetailsWrapper;
  }

// ------------------------------------- Updates a user's details -------------------------------------------

  updateUserDetails() {
    let userDetailsWrapper = this.sessionUserDetailsWrapper;
    userDetailsWrapper.setPassword(this.currentPassword);

    // Sending the info into the password authentication API
    const observable = this.myAuthenticationService.authenticatePassword(userDetailsWrapper);
    observable.subscribe(
     response => {  
          
         if (response.ok) {
          this.isDisplayPasswordWrongError = false;
           userDetailsWrapper.setPassword(this.newPassword);

           if(this.sessionUserDetailsWrapper.userDetails.userType == 'customer') {
            this.myCustomersService.updateCustomer(userDetailsWrapper);
            location.reload();
           } else if (this.sessionUserDetailsWrapper.userDetails.userType == 'company') {
             this.myCompaniesService.updateCompany(userDetailsWrapper);
             location.reload();
           }

         }

       },
       err=>{
        this.isDisplayPasswordWrongError = true;
        console.log(err)
      }
  )
  }

  ngOnInit() {
  }

}
