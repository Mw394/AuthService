package model.exception;

public class NoUserException extends Exception{

    private final Sid sid;

    public NoUserException(Sid sid, String message) {
        super(message);
        this.sid = sid;
    }

    public NoUserException(Sid sid, String message, Throwable cause) {
        super(message, cause);
        this.sid = sid;
    }

    public Sid getSid() {
        return sid;
    }
}
