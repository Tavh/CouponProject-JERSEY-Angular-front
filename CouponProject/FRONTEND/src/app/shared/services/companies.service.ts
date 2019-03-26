import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Company } from '../models/company-object.model';
import { UserDetailsWrapper } from '../models/wrappers/user-details-wrapper.model';


@Injectable({
  providedIn: 'root'
})
export class CompaniesService {

// ----------------------------------------------- Class variables ----------------------------------------------

// ----------------------------------------------- Constructor ---------------------------------------------------

  constructor(private myHttpClient: HttpClient) {
      
   }

// --------------------------------------------- Gets a company by title -----------------------------------------

  public getCompany(name:string) {
      
    return this.myHttpClient.get<Company>(`http://localhost:8080/CouponProject/rest/loggedin/companies/unsecured/bycompanyname?companyName=${name}`, {observe: 'response', withCredentials : true})
}

// ----------------------------------------Updates a company -------------------------------------------------

public updateCompany(userDetailsWrapper: UserDetailsWrapper){
    
    // Communicating with the authentication API in the server    
      this.myHttpClient
      .put<void>("http://localhost:8080/CouponProject/rest/loggedin/companies", userDetailsWrapper.getUserDetails(), {observe: 'response', withCredentials : true})
      .subscribe(
          response => {  
            if(response.ok) {
              alert(userDetailsWrapper.getUsername() + "'s profile updated successfully, navigating to Homepage")
            }
           },
           err=>{
            alert("Company " + userDetailsWrapper.getUsername() + " update failed...")
            console.log(err)
          }
      )
      // The function shouldn't get here, but if it does, it returns a false
    }

// ----------------------------------------Creates a company -------------------------------------------------

  public createCompany(company:Company){

    // Setting an observable object for use in the component
    return this.myHttpClient.post<void>("http://localhost:8080/CouponProject/rest/loggedin/companies/unsecured", company, {observe: 'response', withCredentials : true});
  }
}
