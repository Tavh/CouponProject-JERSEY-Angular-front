import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { LogoutService } from '../shared/services/logout.service';
import { CookieService } from 'ngx-cookie-service';
var LogoutComponent = /** @class */ (function () {
    function LogoutComponent(myLogoutService, myCookieService) {
        this.myLogoutService = myLogoutService;
        this.myCookieService = myCookieService;
    }
    LogoutComponent.prototype.logout = function () {
        this.myLogoutService.logout();
    };
    LogoutComponent.prototype.ngOnInit = function () {
    };
    LogoutComponent = tslib_1.__decorate([
        Component({
            selector: 'app-logout',
            templateUrl: './logout.component.html',
            styleUrls: ['./logout.component.css']
        }),
        tslib_1.__metadata("design:paramtypes", [LogoutService, CookieService])
    ], LogoutComponent);
    return LogoutComponent;
}());
export { LogoutComponent };
//# sourceMappingURL=logout.component.js.map