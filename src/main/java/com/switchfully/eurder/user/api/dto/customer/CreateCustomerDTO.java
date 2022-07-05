package com.switchfully.eurder.user.api.dto.customer;

import com.switchfully.eurder.util.address.api.dto.AddressDTO;
import com.switchfully.eurder.util.address.domain.Address;
import com.switchfully.eurder.util.name.api.dto.NameDTO;
import com.switchfully.eurder.util.name.domain.Name;

public record CreateCustomerDTO(String userName, NameDTO nameDTO, String email, AddressDTO addressDTO, String phoneNumber) {
}
