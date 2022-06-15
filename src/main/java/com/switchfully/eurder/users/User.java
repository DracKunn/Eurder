package com.switchfully.eurder.users;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class User {
    public static final String OWASP_EMAIL_VALIDATION = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    protected final Name name;
    protected final String email;

    protected User(Name name, String email) {
        this.name = validateName(name);
        this.email = validateEmail(email);
    }


    protected static String validateEmail(String email) throws IllegalArgumentException {
        isNotNullOrEmpty(email, "email");
        Pattern pattern = Pattern.compile(OWASP_EMAIL_VALIDATION);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("invalid e-mail format");
        }
        return email;
    }

    protected static Name validateName(Name name) throws IllegalArgumentException {
        isNotNullOrEmpty(name.firstName(), "first name");
        isNotNullOrEmpty(name.lastName(), "last name");
        return name;
    }

    public static void isNotNullOrEmpty(String stringToValidate, String variableFieldName) {
        if (stringToValidate == null || stringToValidate.isBlank()) {
            throw new IllegalArgumentException(variableFieldName + " cannot be empty");
        }
    }



    public Name getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
