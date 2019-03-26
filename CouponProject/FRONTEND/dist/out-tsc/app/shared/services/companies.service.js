import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CompanyWrapper } from '../models/wrappers/company-wrapper.model';
var CompaniesService = /** @class */ (function () {
    // ----------------------------------------------- Constructor ---------------------------------------------------
    function CompaniesService(myHttpClient) {
        this.myHttpClient = myHttpClient;
        // ----------------------------------------------- Class variables ----------------------------------------------
        this.companyWrapper = new CompanyWrapper();
        // Initializes the viewed company as null
        this.companyWrapper.setCompany(undefined);
    }
    // --------------------------------------------- Gets a company by title -----------------------------------------
    CompaniesService.prototype.getCompany = function (name) {
        var _this = this;
        this.myHttpClient
            .get("http://localhost:8080/CouponProject/rest/loggedin/companies/bycompanyname?companyName=" + name, { observe: 'response', withCredentials: true })
            .subscribe(function (res) {
            _this.companyWrapper.company = res.body;
        }, function (err) { console.log(err); });
    };
    // ----------------------------------------Updates a company -------------------------------------------------
    CompaniesService.prototype.updateCompany = function (userDetailsWrapper) {
        // Communicating with the authentication API in the server    
        this.myHttpClient
            .put("http://localhost:8080/CouponProject/rest/loggedin/companies", userDetailsWrapper.getUserDetails(), { observe: 'response', withCredentials: true })
            .subscribe(function (response) {
            if (response.ok) {
                alert(userDetailsWrapper.getUsername() + "'s profile updated successfully");
            }
        }, function (err) {
            alert("Company " + userDetailsWrapper.getUsername() + " update failed...");
            console.log(err);
        });
        // The function shouldn't get here, but if it does, it returns a false
    };
    CompaniesService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient])
    ], CompaniesService);
    return CompaniesService;
}());
export { CompaniesService };
//# sourceMappingURL=companies.service.js.map