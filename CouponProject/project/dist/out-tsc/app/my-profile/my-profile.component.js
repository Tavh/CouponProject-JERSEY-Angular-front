import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { AuthenticationService } from '../shared/services/authentication.service';
import { CustomersService } from '../shared/services/customers.service';
import { CompaniesService } from '../shared/services/companies.service';
var MyProfileComponent = /** @class */ (function () {
    function MyProfileComponent(myAuthenticationService, myCustomersService, myCompaniesService) {
        this.myAuthenticationService = myAuthenticationService;
        this.myCustomersService = myCustomersService;
        this.myCompaniesService = myCompaniesService;
        this.isDisplayPasswordWrongError = false;
        this.sessionUserDetailsWrapper = myAuthenticationService.sessionUserDetailsWrapper;
    }
    MyProfileComponent.prototype.updateUserDetails = function () {
        var _this = this;
        var userDetailsWrapper = this.sessionUserDetailsWrapper;
        userDetailsWrapper.setPassword(this.currentPassword);
        // Sending the info into the password authentication API
        var observable = this.myAuthenticationService.authenticatePassword(userDetailsWrapper);
        observable.subscribe(function (response) {
            if (response.ok) {
                _this.isDisplayPasswordWrongError = false;
                userDetailsWrapper.setPassword(_this.newPassword);
                if (_this.sessionUserDetailsWrapper.userDetails.userType == 'customer') {
                    _this.myCustomersService.updateCustomer(userDetailsWrapper);
                }
                else if (_this.sessionUserDetailsWrapper.userDetails.userType == 'company') {
                    _this.myCompaniesService.updateCompany(userDetailsWrapper);
                }
            }
        }, function (err) {
            _this.isDisplayPasswordWrongError = true;
            console.log(err);
        });
    };
    MyProfileComponent.prototype.ngOnInit = function () {
    };
    MyProfileComponent = tslib_1.__decorate([
        Component({
            selector: 'app-my-profile',
            templateUrl: './my-profile.component.html',
            styleUrls: ['./my-profile.component.css']
        }),
        tslib_1.__metadata("design:paramtypes", [AuthenticationService, CustomersService, CompaniesService])
    ], MyProfileComponent);
    return MyProfileComponent;
}());
export { MyProfileComponent };
//# sourceMappingURL=my-profile.component.js.map