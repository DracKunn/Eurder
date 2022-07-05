package com.switchfully.eurder.users.admin.domain;

import com.switchfully.eurder.users.user.domain.User;
import com.switchfully.eurder.util.name.Name;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends User {

    public Admin(String userName, Name name, String email) {
        super(userName, name, email);
    }
}
