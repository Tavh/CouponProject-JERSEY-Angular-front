export class Company {
    
    id?:number;
    companyName:string;
    password:string;
    email:string;

    constructor (companyName:string, password:string, email:string) {

        this.companyName = companyName;
        this.password = password;
        this.email = email;
    }
}

