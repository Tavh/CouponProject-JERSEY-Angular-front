export class Coupon {
    
    id:number;
    title:string;
    motherCompany:string;
    startDate:string;
    endDate:string;
    amount:number;
    type:string;
    message:string;
    price:number;
    image:string;

    constructor () {
    }

    public getId () {
        return this.id;
    }
}
