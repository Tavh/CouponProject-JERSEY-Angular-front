import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { AuthenticationService } from '../shared/services/authentication.service';
import { Router } from '@angular/router';
var EntranceComponent = /** @class */ (function () {
    function EntranceComponent(myAuthenticationService, myRouter) {
        this.myAuthenticationService = myAuthenticationService;
        this.myRouter = myRouter;
        this.startURLOfChoice = '/home';
        this.sessionUserDetailsWrapper = myAuthenticationService.sessionUserDetailsWrapper;
        this.myRouter.navigate([this.startURLOfChoice]);
    }
    EntranceComponent.prototype.ngOnInit = function () {
    };
    EntranceComponent = tslib_1.__decorate([
        Component({
            selector: 'app-entrance',
            templateUrl: './entrance.component.html',
            styleUrls: ['./entrance.component.css']
        }),
        tslib_1.__metadata("design:paramtypes", [AuthenticationService, Router])
    ], EntranceComponent);
    return EntranceComponent;
}());
export { EntranceComponent };
//# sourceMappingURL=entrance.component.js.map