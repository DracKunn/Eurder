package com.switchfully.eurder.users.customer;

import org.springframework.stereotype.Component;


@Component
public class CustomerMapper {

    public CustomerDTO customerToCustomerDTO(Customer customer){
        return new CustomerDTO(customer.getName(), customer.getEmail(), customer.getAddress(), customer.getPhoneNumber());
    }

    public Customer customerDTOToCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getName(),customerDTO.getEmail(),customerDTO.getAddress(),customerDTO.getPhoneNumber());
    }
}
