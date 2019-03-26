import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDetails } from '../models/user-details.model';
import { UserDetailsWrapper } from '../models/wrappers/user-details-wrapper.model';
import { BooleanWrapper } from '../models/wrappers/boolean-wrapper.model';
var LoginService = /** @class */ (function () {
    // ----------------------------------------------- Constructor ---------------------------------------------------
    function LoginService(myHttpClient) {
        this.myHttpClient = myHttpClient;
        // Initializing isDisplayErrorMessage and isTooManyLoginAttempts as false
        this.isDisplayErrorMessage = new BooleanWrapper(false);
        // Sets the login status in the session storage into "NOT SET" (Whilst the CurrentUser item doesn't exist)
        if (!sessionStorage.getItem('CurrentUser')) {
            var userDetails = new UserDetails();
            userDetails.setLoginStatus("NOT SET");
            sessionStorage.setItem("CurrentUser", JSON.stringify(userDetails));
        }
        this.sessionUserDetailsWrapper = new UserDetailsWrapper();
        this.updateUserDetails();
    }
    // ----------------------------------------- Updates the user details --------------------------------------------
    // This is used to update the user details stored in the service according to the session storage
    LoginService.prototype.updateUserDetails = function () {
        this.sessionUserDetailsWrapper.userDetails = JSON.parse(sessionStorage.getItem('CurrentUser'));
    };
    // ---------------------------------------------- Log-in function ------------------------------------------------
    // This function sends the user details to the server and informs the component of the responded status.
    LoginService.prototype.login = function (userDetails, userTypeSelection) {
        var _this = this;
        // Setting the userType variable according to user selection (Company, Customer or Admin)
        if (userTypeSelection == "company") {
            // Communicating with the company login API in the server    
            this.myHttpClient
                .post("http://localhost:8080/CouponProject/rest/login/logincompany", userDetails, { observe: 'response', withCredentials: true })
                .subscribe(function (response) {
                if (response.ok) {
                    sessionStorage.setItem("CurrentUser", JSON.stringify(response.body));
                    console.log("Log-in permission granted (Company)");
                }
                _this.updateUserDetails();
            }, function (err) {
                _this.isDisplayErrorMessage.setBoolean(true);
                console.log(err);
            });
        }
        else if (userTypeSelection == "customer")
            // Communicating with the customer login API in the server 
            this.myHttpClient
                .post("http://localhost:8080/CouponProject/rest/login/logincustomer", userDetails, { observe: 'response', withCredentials: true })
                .subscribe(function (response) {
                if (response.ok) {
                    sessionStorage.setItem("CurrentUser", JSON.stringify(response.body));
                    console.log("Log-in permission granted (Customer)");
                }
                _this.updateUserDetails();
            }, function (err) {
                _this.isDisplayErrorMessage.setBoolean(true);
                console.log(err);
            });
    };
    LoginService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient])
    ], LoginService);
    return LoginService;
}());
export { LoginService };
//# sourceMappingURL=login.service.js.map