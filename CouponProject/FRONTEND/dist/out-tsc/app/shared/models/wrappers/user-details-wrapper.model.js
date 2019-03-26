var UserDetailsWrapper = /** @class */ (function () {
    function UserDetailsWrapper() {
    }
    UserDetailsWrapper.prototype.setUserDetailsObject = function (userDetails) {
        this.userDetails = userDetails;
    };
    UserDetailsWrapper.prototype.getId = function () {
        return this.userDetails.id;
    };
    UserDetailsWrapper.prototype.getUsername = function () {
        return this.userDetails.username;
    };
    UserDetailsWrapper.prototype.getUserDetails = function () {
        return this.userDetails;
    };
    UserDetailsWrapper.prototype.setUsername = function (username) {
        this.userDetails.username = username;
    };
    UserDetailsWrapper.prototype.setEmail = function (email) {
        this.userDetails.email = email;
    };
    UserDetailsWrapper.prototype.setPassword = function (password) {
        this.userDetails.password = password;
    };
    return UserDetailsWrapper;
}());
export { UserDetailsWrapper };
//# sourceMappingURL=user-details-wrapper.model.js.map