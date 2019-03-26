import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { AuthenticationService } from '../shared/services/authentication.service';
import { UserDetails } from '../shared/models/user-details.model';
var LoginComponent = /** @class */ (function () {
    function LoginComponent(myAuthenticationService) {
        this.myAuthenticationService = myAuthenticationService;
        this.isDisplayErrorMessage = myAuthenticationService.isDisplayErrorMessage;
    }
    LoginComponent.prototype.login = function () {
        this.myAuthenticationService.login(new UserDetails(this.username, this.password, this.userTypeSelection));
        // Resetting the error display to false when done with the login component
        this.myAuthenticationService.isDisplayErrorMessage.setBoolean(false);
    };
    LoginComponent.prototype.ngOnInit = function () {
    };
    LoginComponent = tslib_1.__decorate([
        Component({
            selector: 'app-login',
            templateUrl: './login.component.html',
            styleUrls: ['./login.component.css']
        }),
        tslib_1.__metadata("design:paramtypes", [AuthenticationService])
    ], LoginComponent);
    return LoginComponent;
}());
export { LoginComponent };
//# sourceMappingURL=login.component.js.map