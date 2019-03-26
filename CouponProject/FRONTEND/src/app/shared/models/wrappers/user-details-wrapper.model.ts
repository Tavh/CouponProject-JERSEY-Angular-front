import { UserDetails } from '../user-details.model';

export class UserDetailsWrapper {
    
    userDetails:UserDetails;

    public setUserDetailsObject (userDetails:UserDetails) {
        this.userDetails = userDetails;
    }

    public getId() {
        return this.userDetails.id;
    }
    public getUsername() {
        return this.userDetails.username;
    }
    public getUserDetails () {
        return this.userDetails;
    }

    public setUsername (username:string) {
        this.userDetails.username = username;
    }

    public setEmail (email:string) {
        this.userDetails.email = email;
    }

    public setPassword (password:string) {
        this.userDetails.password = password;
    }

 

    constructor () {

    }
}