import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { CouponArrayWrapper } from '../shared/models/wrappers/coupon-array-wrapper.model';
import { CouponsService } from '../shared/services/coupons.service';
import { AuthenticationService } from '../shared/services/authentication.service';
var PurchasedCouponsComponent = /** @class */ (function () {
    function PurchasedCouponsComponent(myCouponsService, myAuthenticationService) {
        this.myCouponsService = myCouponsService;
        this.myAuthenticationService = myAuthenticationService;
        this.sessionUserDetailsWrapper = myAuthenticationService.sessionUserDetailsWrapper;
        this.couponArrayWrapper = new CouponArrayWrapper();
        this.getPurchasedCoupons();
    }
    PurchasedCouponsComponent.prototype.getPurchasedCoupons = function () {
        var _this = this;
        var customerId = this.sessionUserDetailsWrapper.getId();
        var observable = this.myCouponsService.getPurchasedCoupons(customerId);
        observable.subscribe(function (res) {
            // Deals with array returned as undefined, only one element, and multiple elements
            if (res == undefined) {
                _this.couponArrayWrapper = new CouponArrayWrapper();
                _this.couponArrayWrapper.coupons = [];
            }
            else if (!Array.isArray(res.coupon)) {
                _this.couponArrayWrapper.coupons = [
                    res.coupon
                ];
            }
            else {
                _this.couponArrayWrapper.coupons = res.coupon;
            }
        }, function (err) { console.log(err); });
    };
    PurchasedCouponsComponent.prototype.ngOnInit = function () {
    };
    PurchasedCouponsComponent = tslib_1.__decorate([
        Component({
            selector: 'app-purchased-coupons',
            templateUrl: './purchased-coupons.component.html',
            styleUrls: ['./purchased-coupons.component.css']
        }),
        tslib_1.__metadata("design:paramtypes", [CouponsService, AuthenticationService])
    ], PurchasedCouponsComponent);
    return PurchasedCouponsComponent;
}());
export { PurchasedCouponsComponent };
//# sourceMappingURL=purchased-coupons.component.js.map