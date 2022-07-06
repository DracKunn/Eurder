package com.switchfully.eurder.user.api.dto;

import com.switchfully.eurder.user.domain.Role;
import com.switchfully.eurder.util.address.api.dto.AddressDTO;
import com.switchfully.eurder.util.name.api.dto.NameDTO;

public record UserDTO(int id, String userName, NameDTO nameDTO, String email,
                      AddressDTO addressDTO, String phoneNumber, Role role) {
}


