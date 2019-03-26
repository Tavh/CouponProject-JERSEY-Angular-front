import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDetails } from '../models/user-details.model';
import { UserDetailsWrapper } from '../models/wrappers/user-details-wrapper.model';
import { BooleanWrapper } from '../models/wrappers/boolean-wrapper.model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

// ----------------------------------------------- Class variables ----------------------------------------------

  // Stores the user details currently stored in the session storage
  sessionUserDetailsWrapper:UserDetailsWrapper;
  isDisplayErrorMessage:BooleanWrapper;

// ----------------------------------------------- Constructor ---------------------------------------------------

  constructor(private myHttpClient: HttpClient) {

    // Initializing isDisplayErrorMessage and isTooManyLoginAttempts as false
    this.isDisplayErrorMessage = new BooleanWrapper(false);

    // Sets the login status in the session storage into "NOT SET" (Whilst the CurrentUser item doesn't exist)
    if(!sessionStorage.getItem('CurrentUser')) {
        let userDetails = new UserDetails();
        userDetails.setLoginStatus("Not set");
        sessionStorage.setItem("CurrentUser", JSON.stringify(userDetails));
      }

    this.sessionUserDetailsWrapper = new UserDetailsWrapper();
    this.updateStoredUserDetails();
   }

// ----------------------------------------- Updates the user details --------------------------------------------

   // This is used to update the user details stored in the service according to the session storage
   public updateStoredUserDetails () {
        this.sessionUserDetailsWrapper.userDetails = JSON.parse(sessionStorage.getItem('CurrentUser'));
   }

// ---------------------------------------------- Log-in function ------------------------------------------------

   // This function sends the user details to the server and informs the component of the responded status.
  public login(userDetails: UserDetails) {
    
    // Communicating with the authentication API in the server    
      this.myHttpClient
      .post<UserDetails>("http://localhost:8080/CouponProject/rest/authenticate/login", userDetails, {observe: 'response', withCredentials : true})
      .subscribe(
          response => {  
              
             if (response.ok) {
                 sessionStorage.setItem("CurrentUser", JSON.stringify(response.body));
             }

             this.updateStoredUserDetails();
           },
           err=>{
              this.isDisplayErrorMessage.setBoolean(true);
              console.log(err)
          }
          
      )
    }


// --------------------------------------- Password authentication ------------------------------------------------

    public authenticatePassword (userDetailsWrapper:UserDetailsWrapper) {

       // Setting an observable object for use in the component
      return this.myHttpClient.post<void>("http://localhost:8080/CouponProject/rest/authenticate/authenticatepassword", userDetailsWrapper.getUserDetails(), {observe: 'response', withCredentials : true});
    }
}




// -------------------------------------------------------------------------------------------------------------


