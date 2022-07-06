package com.switchfully.eurder.util.address.service;

import com.switchfully.eurder.util.address.api.dto.AddressDTO;
import com.switchfully.eurder.util.address.api.dto.CreateAddressDTO;
import com.switchfully.eurder.util.address.domain.Address;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public Address toEntity(CreateAddressDTO createAddressDTO) {
        return new Address(createAddressDTO.streetName(), createAddressDTO.streetNumber(), createAddressDTO.postalCode(), createAddressDTO.city());
    }

    public Address toEntity(AddressDTO addressDTO) {
        return new Address(addressDTO.id(),addressDTO.streetName(), addressDTO.streetNumber(), addressDTO.postalCode(), addressDTO.city());
    }

    public AddressDTO toDTO(@NotNull Address address){
        return new AddressDTO(address.getId(), address.getStreetName(), address.getStreetNumber(), address.getPostalCode(), address.getCity());
    }
}
