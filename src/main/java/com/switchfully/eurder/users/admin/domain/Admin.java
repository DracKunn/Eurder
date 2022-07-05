package com.switchfully.eurder.users.admin.domain;

import com.switchfully.eurder.users.user.domain.User;
import com.switchfully.eurder.util.name.Name;

import javax.persistence.Entity;

@Entity
public class Admin extends User {
    public Admin(String userName, Name name, String email) {
        super(id,userName, name, email);
    }
}
