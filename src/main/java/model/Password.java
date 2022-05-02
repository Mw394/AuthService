package model;

import model.exception.Credential;
import model.exception.DomainException;
import model.exception.Sid;

import java.util.regex.Pattern;

public class Password implements Credential {

private final Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");
private final String value;

    public Password(String value) {
        this.value = value;
    }


    @Override
    public String getValue() {
        return value;
    }
}
