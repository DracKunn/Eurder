package com.switchfully.eurder.users.admin;

import org.springframework.stereotype.Component;


@Component
public class AdminMapper {

    public AdminDTO adminToAdminDTO(Admin admin) {
        return new AdminDTO(admin.getUserName(),admin.getName(),admin.getEmail());
    }

    public Admin adminDTOToAdmin(AdminDTO adminDTO){
        return new Admin(adminDTO.getUserName(),adminDTO.getName(),adminDTO.getEmail());
    }
}
