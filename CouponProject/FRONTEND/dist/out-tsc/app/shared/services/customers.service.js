import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
var CustomersService = /** @class */ (function () {
    function CustomersService(myHttpClient) {
        this.myHttpClient = myHttpClient;
    }
    CustomersService.prototype.updateCustomer = function (userDetailsWrapper) {
        // Communicating with the authentication API in the server    
        this.myHttpClient
            .put("http://localhost:8080/CouponProject/rest/loggedin/customers", userDetailsWrapper.getUserDetails(), { observe: 'response', withCredentials: true })
            .subscribe(function (response) {
            if (response.ok) {
                alert(userDetailsWrapper.getUsername() + "'s profile updated successfully");
            }
        }, function (err) {
            alert("Customer " + userDetailsWrapper.getUsername() + " update failed...");
            console.log(err);
        });
        // The function shouldn't get here, but if it does, it returns a false
    };
    CustomersService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient])
    ], CustomersService);
    return CustomersService;
}());
export { CustomersService };
//# sourceMappingURL=customers.service.js.map