package com.switchfully.eurder.users;

public class UserDTO {

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
}
