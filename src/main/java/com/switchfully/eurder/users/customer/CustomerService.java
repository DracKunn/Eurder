package com.switchfully.eurder.users.customer;

import com.switchfully.eurder.users.UserRepository;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CustomerService {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    CustomerMapper customerMapper;
    UserRepository userRepository;

    public CustomerService(CustomerMapper customerMapper, UserRepository userRepository) {
        this.customerMapper = customerMapper;
        this.userRepository = userRepository;
    }

    public void registerNewCustomer(CustomerDTO newCustomerDTO) {
        Customer newCustomer = this.customerMapper.customerDTOToCustomer(newCustomerDTO);
        this.userRepository.addNewUser(newCustomer);
        logger.info("A new customer has been created: " + newCustomer);
    }
}
