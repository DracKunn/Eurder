package com.switchfully.eurder.users.admin.service;

import com.switchfully.eurder.users.admin.api.AdminDTO;
import com.switchfully.eurder.users.admin.domain.Admin;
import org.springframework.stereotype.Component;


@Component
public class AdminMapper {

    public AdminDTO adminToAdminDTO(Admin admin) {
        return new AdminDTO(admin.getUserName(),admin.getName(),admin.getEmail());
    }

    public Admin adminDTOToAdmin(AdminDTO adminDTO){
        return new Admin(adminDTO.userName(),adminDTO.name(),adminDTO.email());
    }
}
