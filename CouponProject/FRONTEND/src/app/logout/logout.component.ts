import { Component, OnInit } from '@angular/core';
import { LogoutService } from '../shared/services/logout.service';
import { CookieService } from 'ngx-cookie-service';
import { UserDetailsWrapper } from '../shared/models/wrappers/user-details-wrapper.model';
import { AuthenticationService } from '../shared/services/authentication.service';
import { UserDetails } from '../shared/models/user-details.model';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

// ------------------------------------- Class variables -------------------------------------------

  sessionUserDetailsWrapper:UserDetailsWrapper;

// ------------------------------------ Constructor ---------------------------------------------

  constructor(private myLogoutService: LogoutService, private myCookieService:CookieService, private myAuthenticationService:AuthenticationService) {
       // Updpates the user details stored in the service
       this.sessionUserDetailsWrapper = myAuthenticationService.sessionUserDetailsWrapper;
   }

// ---------------------------------- Logout -------------------------------
  logout() {
    
    const observable = this.myLogoutService.logout();
    observable.subscribe(
      res => {
        alert("Farewell, " + this.sessionUserDetailsWrapper.userDetails.username + "!");
        sessionStorage.removeItem("CurrentUser")
        this.myCookieService.delete("companyName");
        this.myCookieService.delete("JSESSIONID");

        // Updating the login status into "DISCONNECTED" (If the loginStatus isn't "OK", the user will be denied access to the website)
        let userDetails = new UserDetails();
        userDetails.setLoginStatus("DISCONNECTED");
        sessionStorage.setItem("CurrentUser", JSON.stringify(userDetails));
        this.myAuthenticationService.updateStoredUserDetails();
      },
      err=>{console.log(err)}
  )

  }

  ngOnInit() {
  }

}
