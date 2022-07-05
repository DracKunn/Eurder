package com.switchfully.eurder.user.service.admin;

import com.switchfully.eurder.user.api.dto.admin.AdminDTO;
import com.switchfully.eurder.user.api.dto.admin.CreateAdminDTO;
import com.switchfully.eurder.user.domain.admin.Admin;
import com.switchfully.eurder.util.address.service.AddressMapper;
import com.switchfully.eurder.util.name.service.NameMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class AdminMapper {
    private NameMapper nameMapper;
    public Admin toEntity(CreateAdminDTO createAdminDTO){
        return new Admin(
                createAdminDTO.userName(),
                nameMapper.toEntity(createAdminDTO.nameDTO()),
                createAdminDTO.email());
    }

    public Admin toEntity(AdminDTO adminDTO){
        return new Admin(
                adminDTO.id(),
                adminDTO.userName(),
                nameMapper.toEntity(adminDTO.nameDTO()),
                adminDTO.email());
    }

    public AdminDTO toDTO(Admin admin) {
        return new AdminDTO(
                admin.getId(),
                admin.getUserName(),
                nameMapper.toDTO(admin.getName()),
                admin.getEmail());
    }
}
