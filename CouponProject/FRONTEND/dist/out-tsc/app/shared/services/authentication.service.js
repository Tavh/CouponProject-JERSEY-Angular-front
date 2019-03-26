import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDetails } from '../models/user-details.model';
import { UserDetailsWrapper } from '../models/wrappers/user-details-wrapper.model';
import { BooleanWrapper } from '../models/wrappers/boolean-wrapper.model';
var AuthenticationService = /** @class */ (function () {
    // ----------------------------------------------- Constructor ---------------------------------------------------
    function AuthenticationService(myHttpClient) {
        this.myHttpClient = myHttpClient;
        // Initializing isDisplayErrorMessage and isTooManyLoginAttempts as false
        this.isDisplayErrorMessage = new BooleanWrapper(false);
        // Sets the login status in the session storage into "NOT SET" (Whilst the CurrentUser item doesn't exist)
        if (!sessionStorage.getItem('CurrentUser')) {
            var userDetails = new UserDetails();
            userDetails.setLoginStatus("Not set");
            sessionStorage.setItem("CurrentUser", JSON.stringify(userDetails));
        }
        this.sessionUserDetailsWrapper = new UserDetailsWrapper();
        this.updateStoredUserDetails();
    }
    // ----------------------------------------- Updates the user details --------------------------------------------
    // This is used to update the user details stored in the service according to the session storage
    AuthenticationService.prototype.updateStoredUserDetails = function () {
        this.sessionUserDetailsWrapper.userDetails = JSON.parse(sessionStorage.getItem('CurrentUser'));
    };
    // ---------------------------------------------- Log-in function ------------------------------------------------
    // This function sends the user details to the server and informs the component of the responded status.
    AuthenticationService.prototype.login = function (userDetails) {
        var _this = this;
        // Communicating with the authentication API in the server    
        this.myHttpClient
            .post("http://localhost:8080/CouponProject/rest/authenticate/login", userDetails, { observe: 'response', withCredentials: true })
            .subscribe(function (response) {
            if (response.ok) {
                sessionStorage.setItem("CurrentUser", JSON.stringify(response.body));
            }
            _this.updateStoredUserDetails();
        }, function (err) {
            _this.isDisplayErrorMessage.setBoolean(true);
            console.log(err);
        });
    };
    // --------------------------------------- Password authentication ------------------------------------------------
    AuthenticationService.prototype.authenticatePassword = function (userDetailsWrapper) {
        // Setting an observable object for use in the component
        return this.myHttpClient.post("http://localhost:8080/CouponProject/rest/authenticate/authenticatepassword", userDetailsWrapper.getUserDetails(), { observe: 'response', withCredentials: true });
    };
    AuthenticationService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient])
    ], AuthenticationService);
    return AuthenticationService;
}());
export { AuthenticationService };
// -------------------------------------------------------------------------------------------------------------
//# sourceMappingURL=authentication.service.js.map