package com.switchfully.eurder.user.api.dto.customer;

import com.switchfully.eurder.util.address.api.dto.AddressDTO;
import com.switchfully.eurder.util.name.api.dto.NameDTO;

public record CustomerDTO(int id, String userName, NameDTO nameDTO, String email, AddressDTO addressDTO, String phoneNumber) {


}


