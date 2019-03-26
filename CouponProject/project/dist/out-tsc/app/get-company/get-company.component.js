import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { CompaniesService } from '../shared/services/companies.service';
var GetCompanyComponent = /** @class */ (function () {
    function GetCompanyComponent(myCompaniesService) {
        this.myCompaniesService = myCompaniesService;
        this.companyWrapper = this.myCompaniesService.companyWrapper;
    }
    GetCompanyComponent.prototype.getCompanyFromServer = function () {
        this.myCompaniesService.getCompany(this.name);
    };
    GetCompanyComponent.prototype.ngOnInit = function () {
    };
    GetCompanyComponent = tslib_1.__decorate([
        Component({
            selector: 'app-get-company',
            templateUrl: './get-company.component.html',
            styleUrls: ['./get-company.component.css']
        }),
        tslib_1.__metadata("design:paramtypes", [CompaniesService])
    ], GetCompanyComponent);
    return GetCompanyComponent;
}());
export { GetCompanyComponent };
//# sourceMappingURL=get-company.component.js.map