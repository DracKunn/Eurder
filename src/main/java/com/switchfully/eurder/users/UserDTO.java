package com.switchfully.eurder.users;

import java.util.Objects;

public abstract class UserDTO {

    protected final Name name;
    protected final String email;

    protected UserDTO(Name name, String email) {
        this.name = name;
        this.email = email;
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
        if (!(o instanceof UserDTO userDTO)) return false;
        return getName().equals(userDTO.getName()) && getEmail().equals(userDTO.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail());
    }
}
