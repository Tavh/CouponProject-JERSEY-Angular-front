import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDetailsWrapper } from '../models/wrappers/user-details-wrapper.model';
import { Customer } from '../models/customer-object.model';

@Injectable({
  providedIn: 'root'
})
export class CustomersService {

  constructor(private myHttpClient: HttpClient) {
    
  }

  public getCustomer(email:string) {
    return this.myHttpClient.get<Customer>(`http://localhost:8080/CouponProject/rest/loggedin/customers/unsecured/bycustomeremail?customerEmail=${email}`, {observe: 'response', withCredentials : true})
  }

  // ----------------------------------------Updates a customer's details -------------------------------------------------

  public updateCustomer(userDetailsWrapper: UserDetailsWrapper){
    
    // Communicating with the authentication API in the server    
      this.myHttpClient
      .put<void>("http://localhost:8080/CouponProject/rest/loggedin/customers", userDetailsWrapper.getUserDetails(), {observe: 'response', withCredentials : true})
      .subscribe(
          response => {  
            if(response.ok) {
              alert(userDetailsWrapper.getUsername() + "'s profile updated successfully, navigating to Homepage")
            }
           },
           err=>{
            alert("Customer " + userDetailsWrapper.getUsername() + " update failed...")
            console.log(err)
          }
      )
      // The function shouldn't get here, but if it does, it returns a false
    }

// ----------------------------------------Creates a customer -------------------------------------------------

  public createCustomer(customer:Customer){

  // Setting an observable object for use in the component
  return this.myHttpClient.post<void>("http://localhost:8080/CouponProject/rest/loggedin/customers/unsecured", customer, {observe: 'response', withCredentials : true});
  }
}