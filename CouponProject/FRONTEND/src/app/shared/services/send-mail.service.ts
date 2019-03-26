import { Injectable } from '@angular/core';
import { UserDetails } from '../models/user-details.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SendMailService {

  constructor(private myHttpClient: HttpClient) { }

// ------------------------------------------- Sends a mail after registration ---------------------------------------
  public sendMail (userDetails:UserDetails) {
    
    // Setting an observable object for use in the component
    this.myHttpClient.post<void>("http://localhost:8080/CouponProject/rest/sendmail", userDetails, {observe: 'response', withCredentials : true})
    .subscribe(
      res => {
      },
      err=>{
        console.log(err)
      }
  )
  }
}
