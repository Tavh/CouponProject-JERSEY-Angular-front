import * as tslib_1 from "tslib";
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { GetCompanyComponent } from './get-company/get-company.component';
import * as mainComponent from './main/main.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { BrowseCouponsComponent } from './browse-coupons/browse-coupons.component';
import { EntranceComponent } from './entrance/entrance.component';
import { LogoutComponent } from './logout/logout.component';
import { HomeComponent } from './home/home.component';
import { CookieService } from 'ngx-cookie-service';
import { CustomerViewComponent } from './customer-view/customer-view.component';
import { CompanyViewComponent } from './company-view/company-view.component';
import { MyProfileComponent } from './my-profile/my-profile.component';
import { PurchasedCouponsComponent } from './purchased-coupons/purchased-coupons.component';
var appRoutes = [
    { path: 'home', component: HomeComponent },
    { path: 'get-company', component: GetCompanyComponent },
    { path: 'browse-coupons', component: BrowseCouponsComponent },
    { path: 'my-profile', component: MyProfileComponent },
    { path: 'purchased-coupons', component: PurchasedCouponsComponent },
    { path: '',
        redirectTo: '/home',
        pathMatch: 'full'
    }
];
var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = tslib_1.__decorate([
        NgModule({
            declarations: [
                AppComponent,
                GetCompanyComponent,
                mainComponent.MainComponent,
                HeaderComponent,
                LoginComponent,
                BrowseCouponsComponent,
                EntranceComponent,
                LogoutComponent,
                HomeComponent,
                CustomerViewComponent,
                CompanyViewComponent,
                MyProfileComponent,
                PurchasedCouponsComponent
            ],
            imports: [
                BrowserModule,
                HttpClientModule,
                FormsModule,
                RouterModule.forRoot(appRoutes)
            ],
            providers: [CookieService],
            bootstrap: [AppComponent]
        })
    ], AppModule);
    return AppModule;
}());
export { AppModule };
//# sourceMappingURL=app.module.js.map