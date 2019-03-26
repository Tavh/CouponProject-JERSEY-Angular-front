export class UserDetails {
    username?:string;

    // Including password in the object is only required when logging in!
    password?:string;

    id?:number;
    email?:string;
    loginStatus?:string;
    userType?:string;

    constructor (username?:string, password?:string, userType?:string, id?:number, email?:string, loginStatus?:string, ) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.id = id;
        this.email = email;
        this.loginStatus = loginStatus;
    }
    
    public getUsername () {
        return this.username;
    }

    public setUsername (username:string) {
        this.username = username;
    }

    public getPassword () {
        return this.password;
    }

    public setPassword (password:string) {
        this.password = password;
    }

    
    public getUserType () {
        return this.userType;
    }

    public setUserType (userType:string) {
        this.userType = userType;
    }

    public getId () {
        return this.id;
    }

    public getEmail () {
        return this.email;
    }

    public setEmail (email:string) {
        this.email = email;
    }

    public getLoginStatus () {
        return this.loginStatus;
    }

    public setLoginStatus (loginStatus:string) {
        this.loginStatus = loginStatus;
    }



}