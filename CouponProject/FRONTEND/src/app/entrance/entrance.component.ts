import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../shared/services/authentication.service';
import { Router } from '@angular/router';
import { UserDetailsWrapper } from '../shared/models/wrappers/user-details-wrapper.model';


@Component({
  selector: 'app-entrance',
  templateUrl: './entrance.component.html',
  styleUrls: ['./entrance.component.css']
})
export class EntranceComponent implements OnInit {

// ------------------------------------- Class variables -------------------------------------------

  sessionUserDetailsWrapper:UserDetailsWrapper;
  private startURLOfChoice:string = '/home';
  
// ------------------------------------ Constructor ---------------------------------------------

  constructor(private myAuthenticationService: AuthenticationService, private myRouter: Router) {
    this.sessionUserDetailsWrapper = myAuthenticationService.sessionUserDetailsWrapper;
    this.myRouter.navigate([this.startURLOfChoice]);
   }

  ngOnInit() {
  }

}
