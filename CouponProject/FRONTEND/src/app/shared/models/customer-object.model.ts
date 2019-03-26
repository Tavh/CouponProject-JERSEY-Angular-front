export class Customer {
    
    id?:number;
    customerName:string;
    password:string;
    email:string;

    constructor (customerName:string, password:string, email:string) {

        this.customerName = customerName;
        this.password = password;
        this.email = email;
    }
}

