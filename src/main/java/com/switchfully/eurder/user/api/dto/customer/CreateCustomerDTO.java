package com.switchfully.eurder.user.api.dto.customer;

import com.switchfully.eurder.util.address.api.dto.CreateAddressDTO;
import com.switchfully.eurder.util.name.api.dto.NameDTO;

public record CreateCustomerDTO(String userName, NameDTO nameDTO, String email, CreateAddressDTO createAddressDTO, String phoneNumber) {
}
