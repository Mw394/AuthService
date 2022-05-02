package resources.dto;

public class DTOLoggedIn {

    private final boolean loggedIn;
    private final String userName;

    public DTOLoggedIn(boolean loggedIn, String email) {
        this.loggedIn = loggedIn;
        this.userName = email;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getUserName() {
        return userName;
    }
}
