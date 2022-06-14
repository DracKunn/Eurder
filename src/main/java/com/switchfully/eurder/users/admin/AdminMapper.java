package com.switchfully.eurder.users.admin;

import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class AdminMapper {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    AdminDTO adminToAdminDTO(Admin admin) {
        return new AdminDTO(admin.getName(),admin.getEmail());
    }

    Admin adminDTOToAdmin(AdminDTO adminDTO){
        return new Admin(adminDTO.getName(),adminDTO.getEmail());
    }
}
