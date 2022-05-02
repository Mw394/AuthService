package service.response;

import model.Email;
import model.UserName;

public class SignUpResponse {

    private final Email email;
    private final UserName userName;

    public SignUpResponse(Email email, UserName userName) {
        this.email = email;
        this.userName = userName;
    }

    public Email getEmail() {
        return email;
    }

    public UserName getUserName() {
        return userName;
    }
}
