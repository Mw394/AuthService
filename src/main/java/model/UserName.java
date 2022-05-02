package model;

import model.exception.Credential;
import model.exception.DomainException;
import model.exception.Sid;

public class UserName implements Credential {

private final String value;

    public UserName(String value) {
        this.value = value;
    }

    public static boolean isUsername(String value) {
        return !(value == null || value.trim().length() != value.length() || value.length() > 20 || value.contains("@"));
    }

    @Override
    public String getValue() {
        return value;
    }
}
