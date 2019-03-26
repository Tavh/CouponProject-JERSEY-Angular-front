export class CouponTransaction {

    customerId:number;    
    couponId:number;

    constructor (customerId:number, couponId:number) {
        this.customerId = customerId;
        this.couponId = couponId;
    }
}