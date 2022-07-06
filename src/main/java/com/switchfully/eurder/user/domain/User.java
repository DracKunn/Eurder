package com.switchfully.eurder.user.domain;


import com.switchfully.eurder.util.name.domain.Name;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

import static com.switchfully.eurder.util.validation.ValidatorsUtility.*;

//https://stackoverflow.com/questions/64417858/spring-jpa-join-abstract-class-in-abstract-class
//https://www.baeldung.com/hibernate-inheritance
@Entity(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type", discriminatorType = DiscriminatorType.INTEGER)
@Table
@NoArgsConstructor
@Getter
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_id_seq", allocationSize = 1)
    protected int id;
    @Column
    protected String userName;
    @Transient
    protected Name name;
    @Column
    protected String fullName;
    @Column
    protected String email;

    protected User(String userName, Name name, String email) throws IllegalArgumentException {
        this.userName = validateURLFriendly(userName);
        this.name = validateName(name);
        this.email = validateEmail(email);
        this.fullName = name.toString();
    }

    protected User(int id, String userName, Name name, String email) throws IllegalArgumentException {
        this.id = id;
        this.userName = validateURLFriendly(userName);
        this.name = validateName(name);
        this.email = validateEmail(email);
        this.fullName = name.toString();
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
