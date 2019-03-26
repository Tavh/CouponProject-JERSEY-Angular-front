import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { AuthenticationService } from './authentication.service';
import { UserDetails } from '../models/user-details.model';
var LogoutService = /** @class */ (function () {
    // ----------------------------------------------- Constructor ---------------------------------------------------
    function LogoutService(myHttpClient, myCookieService, myAuthenticationService) {
        this.myHttpClient = myHttpClient;
        this.myCookieService = myCookieService;
        this.myAuthenticationService = myAuthenticationService;
        // Updpates the user details stored in the service
        this.sessionUserDetailsWrapper = myAuthenticationService.sessionUserDetailsWrapper;
    }
    // ---------------------------------------------- Log-out function ---------------------------------------------
    LogoutService.prototype.logout = function () {
        var _this = this;
        // Communicating with the logout API in the server
        this.myHttpClient
            .get("http://localhost:8080/CouponProject/rest/logout", { observe: 'response', withCredentials: true })
            .subscribe(function (res) {
            alert("Farewell, " + _this.sessionUserDetailsWrapper.userDetails.username + "!");
            sessionStorage.removeItem("CurrentUser");
            _this.myCookieService.delete("companyName");
            _this.myCookieService.delete("JSESSIONID");
            // Updating the login status into "DISCONNECTED" (If the loginStatus isn't "OK", the user will be denied access to the website)
            var userDetails = new UserDetails();
            userDetails.setLoginStatus("DISCONNECTED");
            sessionStorage.setItem("CurrentUser", JSON.stringify(userDetails));
            _this.myAuthenticationService.updateStoredUserDetails();
        }, function (err) { console.log(err); });
    };
    LogoutService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient, CookieService, AuthenticationService])
    ], LogoutService);
    return LogoutService;
}());
export { LogoutService };
//# sourceMappingURL=logout.service.js.map