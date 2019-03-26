import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../shared/services/authentication.service';
import { UserDetailsWrapper } from '../shared/models/wrappers/user-details-wrapper.model';
import { LogoutService } from '../shared/services/logout.service';
import { CookieService } from 'ngx-cookie-service';
import { UserDetails } from '../shared/models/user-details.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

// ------------------------------------- Class variables -------------------------------------------

  sessionUserDetailsWrapper:UserDetailsWrapper;

// ------------------------------------ Constructor ---------------------------------------------

  constructor(private myAuthenticationService:AuthenticationService) {
    // Updpates the user details stored in the service
    this.sessionUserDetailsWrapper = myAuthenticationService.sessionUserDetailsWrapper;
}

  ngOnInit() {
  }

}
