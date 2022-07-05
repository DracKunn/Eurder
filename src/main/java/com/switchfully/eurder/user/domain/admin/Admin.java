package com.switchfully.eurder.user.domain.admin;

import com.switchfully.eurder.user.domain.User;
import com.switchfully.eurder.util.name.domain.Name;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@NoArgsConstructor
public class Admin extends User {
    public Admin(String userName, Name name, String email) {
        super(userName, name, email);
    }

    public Admin(int id, String userName, Name name, String email) {
        super(id,userName, name, email);
    }
}
