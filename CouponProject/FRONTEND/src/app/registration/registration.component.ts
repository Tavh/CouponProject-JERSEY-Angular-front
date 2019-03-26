import { Component, OnInit } from '@angular/core';
import { CompaniesService } from '../shared/services/companies.service';
import { CustomersService } from '../shared/services/customers.service';
import { UserDetails } from '../shared/models/user-details.model';
import { Company } from '../shared/models/company-object.model';
import { Customer } from '../shared/models/customer-object.model';
import { HttpErrorResponse } from '@angular/common/http';
import { SendMailService } from '../shared/services/send-mail.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

// ------------------------------------- Class variables -------------------------------------------

  userTypeSelection:string;
  name:string;
  email:string;
  password:string;

// ------------------------------------ Constructor ---------------------------------------------

  constructor(private myCompaniesService:CompaniesService, private myCustomersService:CustomersService, private mySendMailService:SendMailService) {
   }

// ------------------------------------- Creates a user -------------------------------------------

  createUser () {
    
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~ If it's a company ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    if (this.userTypeSelection == 'company') {

      let company = new Company(this.name, this.password, this.email);

      console.log(company);

      const observable =  this.myCompaniesService.createCompany(company);
      observable.subscribe(
        res => {
               // Making a UserDetails object in order to send a mail
               let userDetails = new UserDetails();
               userDetails.setUsername(company.companyName);
               userDetails.setPassword(company.password);
               userDetails.setEmail(company.email);
               // Using the SendMailService to send the client a mail with his details
               this.mySendMailService.sendMail(userDetails);
               alert("Your account was created! An email with your user details has been sent to " + userDetails.email)
      },
      err=>{
        alert("Something went wrong during user creation..");
        console.log(err)}
    )

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~ If it's a customer ~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    } else if (this.userTypeSelection == 'customer') {
      
      let customer = new Customer(this.name, this.password, this.email);

      console.log(customer);

      const observable =  this.myCustomersService.createCustomer(customer);
      observable.subscribe(
        res => {
            // Making a UserDetails object in order to send a mail
            let userDetails = new UserDetails();
            userDetails.setUsername(customer.customerName);
            userDetails.setPassword(customer.password);
            userDetails.setEmail(customer.email);
            // Using the SendMailService to send the client a mail with his details
            this.mySendMailService.sendMail(userDetails);
            alert("Your account was created! An email with your user details has been sent to " + userDetails.email)
            location.reload();
      },
      err=>{
        alert("Something went wrong during user registration...")
        console.log(err)}
    )
    }
   
  }

// ------------------------------------- Checks username availability -------------------------------------------

  checkAvailability () {
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~ If it's a company ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      if (this.userTypeSelection == 'company') {

        const observable =  this.myCompaniesService.getCompany(this.name);
        observable.subscribe(
          res => {
            if(res.body == null) {
              alert("This company name is available!");
            } else if (res.body.companyName == this.name) {
              alert("This company is already registered...");
            }
          },
          err=>{
            console.log(err)
          }
      )
      
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~ If it's a company ~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    } else if (this.userTypeSelection == 'customer') {
      const observable =  this.myCustomersService.getCustomer(this.email);
      observable.subscribe(
        res => {
          if(res.body == null) {
            alert("This email is available!");
          } else if (res.body.email == this.email) {
            alert("This email is already in use...");
          }
        },
        err=>{
          console.log(err)
        }
      )
    }
  }
  ngOnInit() {
  }

}