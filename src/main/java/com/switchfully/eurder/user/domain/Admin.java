package com.switchfully.eurder.user.domain;

import com.switchfully.eurder.util.name.domain.Name;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
@NoArgsConstructor
public class Admin extends User {
    public Admin(String userName, Name name, String email) {
        super(userName, name, email);
    }

    public Admin(int id, String userName, Name name, String email) {
        super(id, userName, name, email);
    }
}
