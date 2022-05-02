package resources.dto;

import model.Email;
import model.UserName;
import model.Password;

public class DTOSignUp {
    private final Email email;
    private final UserName userName;
    private final Password password;

    public DTOSignUp(Email email, UserName userName, Password password) {
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
