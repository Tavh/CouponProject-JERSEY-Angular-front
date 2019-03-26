import { Component, OnInit } from '@angular/core';
import { UserDetailsWrapper } from '../shared/models/wrappers/user-details-wrapper.model';
import { AuthenticationService } from '../shared/services/authentication.service';

@Component({
  selector: 'app-customer-view',
  templateUrl: './customer-view.component.html',
  styleUrls: ['./customer-view.component.css']
})
export class CustomerViewComponent implements OnInit {

// ------------------------------------- Class variables -------------------------------------------

  sessionUserDetailsWrapper:UserDetailsWrapper;

// ------------------------------------ Constructor ---------------------------------------------

  constructor(private myAuthenticationService:AuthenticationService) {
    this.sessionUserDetailsWrapper = myAuthenticationService.sessionUserDetailsWrapper;
   }

  ngOnInit() {
  }

}
