package model;

import model.exception.Credential;
import model.exception.DomainException;
import model.exception.Sid;

import java.util.regex.Pattern;

public class Email implements Credential {
    private static final Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
    private final String value;

    public Email(String value) {
        this.value = value;
    }

    public static boolean isEmail(String value) { return pattern.matcher(value).matches();}

    @Override
    public String getValue() {
        return value;
    }
}
