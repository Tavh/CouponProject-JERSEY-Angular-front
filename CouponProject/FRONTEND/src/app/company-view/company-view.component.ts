import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../shared/services/authentication.service';
import { UserDetailsWrapper } from '../shared/models/wrappers/user-details-wrapper.model';

@Component({
  selector: 'app-company-view',
  templateUrl: './company-view.component.html',
  styleUrls: ['./company-view.component.css']
})
export class CompanyViewComponent implements OnInit {

// ------------------------------------- Class variables -------------------------------------------

  sessionUserDetailsWrapper:UserDetailsWrapper;

// ------------------------------------ Constructor ---------------------------------------------

  constructor(private myAuthenticationService:AuthenticationService) {
    this.sessionUserDetailsWrapper = myAuthenticationService.sessionUserDetailsWrapper;
   }

  ngOnInit() {
  }

}
