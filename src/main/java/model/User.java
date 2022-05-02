package model;

public class User {

    private final Id id;
    private final Email email;
    private final UserName userName;
    private final Password password;

    public User(Id id, Email email, UserName userName, Password password) {
    this.id = id;
    this.email = email;
    this.userName = userName;
    this.password = password;
    }

    public Id getId() {
        return id;
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
