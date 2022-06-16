package com.switchfully.eurder.users.customer;

import com.switchfully.eurder.users.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public CustomerDTO registerNewCustomer(CustomerDTO newCustomerDTO) {
        Customer newCustomer = this.customerMapper.customerDTOToCustomer(newCustomerDTO);
        this.userRepository.addNewCustomer(newCustomer);
        logger.info("A new customer has been created: " + newCustomer);
        return newCustomerDTO;
    }

    private List<Customer> getAllCustomers() {
        return userRepository.getAllCustomers();
    }

    public List<CustomerDTO> viewAllCustomers() {
        List<Customer> customers = this.getAllCustomers();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : customers) {
            customerDTOS.add(customerMapper.customerToCustomerDTO(customer));
        }
        return customerDTOS;
    }

    public CustomerDTO getCustomerByEmail(String email) {
        Customer customer = this.userRepository.getCustomerByEmail(email);
        return this.customerMapper.customerToCustomerDTO(customer);
    }
}
