package com.switchfully.eurder.users;


import com.switchfully.eurder.util.ValidatorsUtility;

import java.util.Objects;
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
        ValidatorsUtility.isNotNullOrEmpty(email, "email");
        Pattern pattern = Pattern.compile(OWASP_EMAIL_VALIDATION);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("invalid e-mail format");
        }
        return email;
    }

    protected static Name validateName(Name name) throws IllegalArgumentException {
        ValidatorsUtility.isNotNullOrEmpty(name.firstName(), "first name");
        ValidatorsUtility.isNotNullOrEmpty(name.lastName(), "last name");
        return name;
    }

    public Name getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getName().equals(user.getName()) && getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail());
    }
}
