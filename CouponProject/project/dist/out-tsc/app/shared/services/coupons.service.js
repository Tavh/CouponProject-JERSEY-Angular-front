import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CouponArrayWrapper } from '../models/wrappers/coupon-array-wrapper.model';
var CouponsService = /** @class */ (function () {
    // ----------------------------------------------- Constructor ---------------------------------------------------
    function CouponsService(myHttpClient) {
        this.myHttpClient = myHttpClient;
        this.couponArrayWrapper = new CouponArrayWrapper();
        this.getAllCoupons();
    }
    // ----------------------------------------- Gets all the coupons in the db --------------------------------------
    CouponsService.prototype.getAllCoupons = function () {
        var _this = this;
        this.myHttpClient
            .get("http://localhost:8080/CouponProject/rest/coupons/allcoupons")
            .subscribe(function (res) {
            // Deals with array returned as undefined, only one element, and multiple elements
            if (res == undefined) {
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
    // ----------------------------------------- Gets a coupon by title --------------------------------------
    CouponsService.prototype.getCouponByTitle = function (title) {
        var _this = this;
        this.myHttpClient
            .get("http://localhost:8080/CouponProject/rest/coupons/bytitle?couponTitle=" + title)
            .subscribe(function (res) {
            _this.couponArrayWrapper.coupons = [
                res
            ];
        }, function (err) { console.log(err); });
    };
    // --------------------------------- Gets all the coupons of a certain type -------------------------------
    CouponsService.prototype.getCouponsByType = function (couponType) {
        var _this = this;
        this.myHttpClient
            .get("http://localhost:8080/CouponProject/rest/coupons/bytype?couponType=" + couponType)
            .subscribe(function (res) {
            // Deals with array returned as undefined, only one element, and multiple elements
            if (res == undefined) {
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
    // --------------------------------- Purchases a coupon -------------------------------
    CouponsService.prototype.purchaseCoupon = function (couponTransaction) {
        // Setting an observable object for use in the component
        return this.myHttpClient.post("http://localhost:8080/CouponProject/rest/coupons/purchasecoupon", couponTransaction, { observe: 'response', withCredentials: true });
    };
    // --------------------------------- Gets coupons purchased by a specific customer -------------------------------
    CouponsService.prototype.getPurchasedCoupons = function (customerId) {
        // Setting an observable object for use in the component
        return this.myHttpClient.get("http://localhost:8080/CouponProject/rest/coupons/purchasedcoupons?customerId=" + customerId);
    };
    // -------------------------- Gets all the coupons that were made by a certain company ---------------------------
    CouponsService.prototype.getCouponsByCompany = function (companyName) {
        // Setting an observable object for use in the component
        return this.myHttpClient.get("http://localhost:8080/CouponProject/rest/coupons/companycoupons?companyName=" + companyName);
    };
    CouponsService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient])
    ], CouponsService);
    return CouponsService;
}());
export { CouponsService };
//# sourceMappingURL=coupons.service.js.map