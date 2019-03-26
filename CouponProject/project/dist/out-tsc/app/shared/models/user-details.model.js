var UserDetails = /** @class */ (function () {
    function UserDetails(username, password, userType, id, email, loginStatus) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.id = id;
        this.email = email;
        this.loginStatus = loginStatus;
    }
    UserDetails.prototype.getUsername = function () {
        return this.username;
    };
    UserDetails.prototype.setUsername = function (username) {
        this.username = username;
    };
    UserDetails.prototype.getPassword = function () {
        return this.password;
    };
    Object.defineProperty(UserDetails.prototype, "_password", {
        set: function (password) {
            this.password = password;
        },
        enumerable: true,
        configurable: true
    });
    UserDetails.prototype.getUserType = function () {
        return this.userType;
    };
    UserDetails.prototype.setUserType = function (userType) {
        this.userType = userType;
    };
    UserDetails.prototype.getId = function () {
        return this.id;
    };
    UserDetails.prototype.getEmail = function () {
        return this.email;
    };
    UserDetails.prototype.setEmail = function (email) {
        this.email = email;
    };
    UserDetails.prototype.getLoginStatus = function () {
        return this.loginStatus;
    };
    UserDetails.prototype.setLoginStatus = function (loginStatus) {
        this.loginStatus = loginStatus;
    };
    return UserDetails;
}());
export { UserDetails };
//# sourceMappingURL=user-details.model.js.map