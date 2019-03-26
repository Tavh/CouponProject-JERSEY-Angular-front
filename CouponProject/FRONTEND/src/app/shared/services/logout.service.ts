import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LogoutService {

// ----------------------------------------------- Constructor ---------------------------------------------------

  constructor(private myHttpClient: HttpClient) { 
  }

// ---------------------------------------------- Log-out function ---------------------------------------------

  public logout() {

    // Communicating with the logout API in the server
    return this.myHttpClient.get<void>("http://localhost:8080/CouponProject/rest/logout", {observe: 'response', withCredentials : true});
 
  }

// -------------------------------------------------------------------------------------------------------------

}

