package com.switchfully.eurder.users.admin;

import com.switchfully.eurder.users.Name;
import com.switchfully.eurder.users.UserDTO;

public class AdminDTO extends UserDTO {
    public AdminDTO(String userName,Name name, String email) {
        super(userName,name, email);
    }

}
