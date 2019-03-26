import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
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
import { CompanyCouponsComponent } from './company-coupons/company-coupons.component';
import { CreateCouponComponent } from './create-coupon/create-coupon.component';
import { RegistrationComponent } from './registration/registration.component';
import { PreLoginComponent } from './pre-login/pre-login.component';


const appRoutes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'browse-coupons', component: BrowseCouponsComponent },
  { path: 'my-profile', component: MyProfileComponent },
  { path: 'purchased-coupons', component: PurchasedCouponsComponent },
  { path: 'company-coupons', component: CompanyCouponsComponent },
  { path: 'create-coupon', component: CreateCouponComponent },
  { path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  }
];

@NgModule({
  declarations: [
    AppComponent,
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
    PurchasedCouponsComponent,
    CompanyCouponsComponent,
    CreateCouponComponent,
    RegistrationComponent,
    PreLoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [ CookieService ],
  bootstrap: [AppComponent]
})
export class AppModule { }