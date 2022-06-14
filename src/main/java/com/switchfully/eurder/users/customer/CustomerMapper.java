package com.switchfully.eurder.users.customer;

import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class CustomerMapper {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    CustomerDTO customerToCustomerDTO(Customer customer){
        return new CustomerDTO(customer.getName(), customer.getEmail(), customer.getAddress(), customer.getPhoneNumber());
    }

    Customer customerDTOToCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getName(),customerDTO.getEmail(),customerDTO.getAddress(),customerDTO.getPhoneNumber());
    }
}
