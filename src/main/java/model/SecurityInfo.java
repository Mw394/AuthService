package model;

public class SecurityInfo {

    private final Id userid;
    private final UserName userName;

    public SecurityInfo(Id userId, UserName username) {
        this.userid = userId;
        this.userName = username;
    }

    public Id getUserid() { return userid; }

    public UserName getUsername() { return userName; }
}
