import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../shared/services/authentication.service';
import { UserDetails } from '../shared/models/user-details.model';
import { BooleanWrapper } from '../shared/models/wrappers/boolean-wrapper.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

// ------------------------------ Class variables -------------------------------

  isDisplayErrorMessage: BooleanWrapper;
  userTypeSelection: string;
  username: string;
  password: string;

// ------------------------------ Constructor -------------------------------

  constructor(private myAuthenticationService: AuthenticationService) {
    this.isDisplayErrorMessage = myAuthenticationService.isDisplayErrorMessage;
  }

// ------------------------------ Login function -------------------------------

  // The login service handles the http request
  login() {
       this.myAuthenticationService.login(new UserDetails(this.username, this.password, this.userTypeSelection))

       // Resetting the error display to false when done with the login component
       this.myAuthenticationService.isDisplayErrorMessage.setBoolean(false);
  }

  ngOnInit() {


  }

}
