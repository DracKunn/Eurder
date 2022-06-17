package com.switchfully.eurder.users;


import java.util.Objects;

import static com.switchfully.eurder.util.ValidatorsUtility.*;

public abstract class User {

    protected final Name name;
    protected final String email;

    protected User(Name name, String email) throws IllegalArgumentException {
        this.name = validateName(name);
        this.email = validateEmail(email);
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
