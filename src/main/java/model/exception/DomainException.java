package model.exception;

public class DomainException extends Exception{

    private final Sid sid;

    public DomainException(Sid sid, String message) {
        super(message);
        this.sid = sid;
    }

    public DomainException(Sid sid, String message, Throwable cause) {
        super(message, cause);
        this.sid = sid;
    }

    public Sid getSid() {
        return sid;
    }
}
