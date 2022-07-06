package com.switchfully.eurder.user.service;

import com.switchfully.eurder.user.api.dto.customer.CreateCustomerDTO;
import com.switchfully.eurder.user.api.dto.customer.CustomerDTO;
import com.switchfully.eurder.user.domain.Customer;
import com.switchfully.eurder.util.address.service.AddressMapper;
import com.switchfully.eurder.util.name.service.NameMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CustomerMapper {

    private NameMapper nameMapper;
    private AddressMapper addressMapper;

    public Customer toEntity(CreateCustomerDTO createCustomerDTO) {
        return new Customer(
                createCustomerDTO.userName(),
                nameMapper.toEntity(createCustomerDTO.nameDTO()),
                createCustomerDTO.email(),
                addressMapper.toEntity(createCustomerDTO.createAddressDTO()),
                createCustomerDTO.phoneNumber()
        );
    }

    public Customer toEntity(CustomerDTO customerDTO) {
        return new Customer(
                customerDTO.id(),
                customerDTO.userName(),
                nameMapper.toEntity(customerDTO.nameDTO()),
                customerDTO.email(),
                addressMapper.toEntity(customerDTO.addressDTO()),
                customerDTO.phoneNumber()
        );
    }

    public CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getUserName(),
                nameMapper.toDTO(customer.getName()),
                customer.getEmail(),
                addressMapper.toDTO(customer.getAddress()),
                customer.getPhoneNumber()
                );
    }
}
