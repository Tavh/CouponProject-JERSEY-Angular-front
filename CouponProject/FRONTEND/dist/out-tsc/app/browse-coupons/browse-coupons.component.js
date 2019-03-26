import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { CouponsService } from '../shared/services/coupons.service';
import { AuthenticationService } from '../shared/services/authentication.service';
import { CouponTransaction } from '../shared/models/coupon-transaction.model';
var BrowseCouponsComponent = /** @class */ (function () {
    function BrowseCouponsComponent(myCouponsService, myAuthenticationService) {
        this.myCouponsService = myCouponsService;
        this.myAuthenticationService = myAuthenticationService;
        this.couponArrayWrapper = this.myCouponsService.couponArrayWrapper;
        this.sessionUserDetailsWrapper = myAuthenticationService.sessionUserDetailsWrapper;
        // Setting the date as today
        this.today = new Date();
    }
    BrowseCouponsComponent.prototype.ngOnInit = function () {
        sessionStorage.getItem('loginStatus');
    };
    BrowseCouponsComponent.prototype.getCoupon = function () {
        this.myCouponsService.getCouponByTitle(this.title);
    };
    BrowseCouponsComponent.prototype.getAllCoupons = function () {
        this.myCouponsService.getAllCoupons();
    };
    BrowseCouponsComponent.prototype.getCouponsByType = function () {
        this.myCouponsService.getCouponsByType(this.typeSelection);
    };
    BrowseCouponsComponent.prototype.compareDates = function (date) {
        if (new Date(date) < this.today) {
            return true;
        }
        return false;
    };
    BrowseCouponsComponent.prototype.purchaseCoupon = function (coupon) {
        var _this = this;
        var couponTransaction = new CouponTransaction(this.sessionUserDetailsWrapper.getId(), coupon.id);
        console.log(couponTransaction);
        var observable = this.myCouponsService.purchaseCoupon(couponTransaction);
        observable.subscribe(function (response) {
            if (response.ok) {
                console.log(couponTransaction);
                _this.myCouponsService.getAllCoupons();
                alert('"' + coupon.title + '"' + " was purchased successfully");
            }
        }, function (err) {
            alert("Coupon purchase failed");
            console.log(err);
        });
    };
    BrowseCouponsComponent = tslib_1.__decorate([
        Component({
            selector: 'app-browse-coupons',
            templateUrl: './browse-coupons.component.html',
            styleUrls: ['./browse-coupons.component.css']
        }),
        tslib_1.__metadata("design:paramtypes", [CouponsService, AuthenticationService])
    ], BrowseCouponsComponent);
    return BrowseCouponsComponent;
}());
export { BrowseCouponsComponent };
//# sourceMappingURL=browse-coupons.component.js.map