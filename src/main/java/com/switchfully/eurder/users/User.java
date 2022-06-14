package com.switchfully.eurder.users;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class User {
    public static final String OWASP_EMAIL_VALIDATION = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    protected final Name name;
    protected final String email;

    protected User(Name name, String email) {
        this.name = name;
        this.email = validateEmail(email);
    }


    protected static String validateEmail(String email) throws IllegalArgumentException {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        Pattern pattern = Pattern.compile(OWASP_EMAIL_VALIDATION);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("invalid e-mail format");
        }
        return email;
    }

    public Name getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
