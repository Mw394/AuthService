package service.request;

import model.Email;
import model.Password;
import model.UserName;

public class LoginRequest {

    private final Email email;
    private final UserName userName;
    private final Password password;

    public LoginRequest(Email email, UserName userName, Password password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public Email getEmail() {
        return email;
    }

    public UserName getUserName() {
        return userName;
    }

    public Password getPassword() {
        return password;
    }
}
