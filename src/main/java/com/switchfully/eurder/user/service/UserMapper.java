package com.switchfully.eurder.user.service;

import com.switchfully.eurder.user.api.dto.CreateUserDTO;
import com.switchfully.eurder.user.api.dto.UserDTO;
import com.switchfully.eurder.user.domain.User;
import com.switchfully.eurder.util.address.service.AddressMapper;
import com.switchfully.eurder.util.name.service.NameMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class UserMapper {

    private NameMapper nameMapper;
    private AddressMapper addressMapper;

    public User toEntity(CreateUserDTO createUserDTO) {
        return new User(
                createUserDTO.userName(),
                nameMapper.toEntity(createUserDTO.nameDTO()),
                createUserDTO.email(),
                addressMapper.toEntity(createUserDTO.createAddressDTO()),
                createUserDTO.phoneNumber(),
                createUserDTO.role()

        );
    }

    public User toEntity(UserDTO userDTO) {
        return new User(
                userDTO.id(),
                userDTO.userName(),
                nameMapper.toEntity(userDTO.nameDTO()),
                userDTO.email(),
                addressMapper.toEntity(userDTO.addressDTO()),
                userDTO.phoneNumber(), userDTO.role()
        );
    }

    public UserDTO toDTO(User customer) {
        return new UserDTO(
                customer.getId(),
                customer.getUserName(),
                nameMapper.toDTO(customer.getName()),
                customer.getEmail(),
                addressMapper.toDTO(customer.getAddress()),
                customer.getPhoneNumber()
                ,customer.getUserRole()
                );
    }
}
