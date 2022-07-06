package com.switchfully.eurder.user.api.dto;

import com.switchfully.eurder.user.domain.Role;
import com.switchfully.eurder.util.address.api.dto.CreateAddressDTO;
import com.switchfully.eurder.util.name.api.dto.NameDTO;

public record CreateUserDTO(String userName, NameDTO nameDTO, String email,
                            CreateAddressDTO createAddressDTO, String phoneNumber, Role role) {
}
