import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pre-login',
  templateUrl: './pre-login.component.html',
  styleUrls: ['./pre-login.component.css']
})
export class PreLoginComponent implements OnInit {

  isViewRegistrationForm:boolean;
  
  constructor() {
    this.isViewRegistrationForm = false;
   }

  ngOnInit() {
  }

}
