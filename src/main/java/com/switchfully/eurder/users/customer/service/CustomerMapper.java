package com.switchfully.eurder.users.customer.service;

import com.switchfully.eurder.users.customer.api.dto.CustomerDTO;
import com.switchfully.eurder.users.customer.domain.Customer;
import org.springframework.stereotype.Component;


@Component
public class CustomerMapper {
//    private final java.util.logging.Logger logger = Logger.getLogger(this.getClass().getName());


    public CustomerDTO customerToCustomerDTO(Customer customer){
//        logger.info("Customer transformed to DTO");
        return new CustomerDTO(customer.getUserName(),
                customer.getName(), customer.getEmail(), customer.getAddress(), customer.getPhoneNumber(),customer.getAllOrders());
    }

    public Customer customerDTOToCustomer(CustomerDTO customerDTO){
//        logger.info("DTO transformed to Customer");
        return new Customer(customerDTO.userName(),customerDTO.name(),customerDTO.email(),customerDTO.address(),customerDTO.phoneNumber(),customerDTO.orders());
    }
}
