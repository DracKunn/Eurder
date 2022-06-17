package com.switchfully.eurder.users;

import java.util.Objects;

import static com.switchfully.eurder.util.ValidatorsUtility.*;

public abstract class UserDTO {

    protected final String userName;
    protected final Name name;
    protected final String email;

    protected UserDTO(String userName, Name name, String email) throws IllegalArgumentException {
        this.userName = validateURLFriendly(userName);
        this.name = validateName(name);
        this.email = validateEmail(email);
    }

    public String getUserName() {
        return userName;
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
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return getUserName().equals(userDTO.getUserName()) && getName().equals(userDTO.getName()) && getEmail().equals(userDTO.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getName(), getEmail());
    }
}
