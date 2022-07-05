package com.switchfully.eurder.users.user.domain;


import com.switchfully.eurder.util.name.Name;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

import static com.switchfully.eurder.util.validation.ValidatorsUtility.*;
@MappedSuperclass
@Table
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_id_seq", allocationSize = 1)
    protected int id;
    @Column
    protected final String userName;
    @Column
    protected final Name name;
    @Column
    protected final String email;

    protected User(String userName, Name name, String email) throws IllegalArgumentException {
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
        if (!(o instanceof User user)) return false;
        return getUserName().equals(user.getUserName()) && getName().equals(user.getName()) && getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getName(), getEmail());
    }
}
