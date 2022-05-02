package model.exception;

public class InternalServerException extends RuntimeException{
    private final Sid sid;

    public InternalServerException(Sid sid, String message) {
        super(message);
        this.sid = sid;
    }

    public InternalServerException(Sid sid, String message, Throwable cause) {
        super(message, cause);
        this.sid = sid;
    }

    public Sid getSid() {
        return sid;
    }
}
