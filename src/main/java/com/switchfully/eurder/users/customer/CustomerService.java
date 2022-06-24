package com.switchfully.eurder.users.customer;

import com.switchfully.eurder.users.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.switchfully.eurder.util.ValidatorsUtility.isNotNull;


@Service
public class CustomerService {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public CustomerMapper customerMapper;
    UserRepository userRepository;

    public CustomerService(CustomerMapper customerMapper, UserRepository userRepository) {
        this.customerMapper = customerMapper;
        this.userRepository = userRepository;
    }

    public CustomerDTO registerNewCustomer(CustomerDTO newCustomerDTO) throws IllegalArgumentException{
        isNotNull(newCustomerDTO, "new customer DTO");
        Customer newCustomer = this.customerMapper.customerDTOToCustomer(newCustomerDTO);
        checkIfCustomerAlreadyExits(newCustomer);
        this.userRepository.addNewCustomer(newCustomer);
//        logger.info("A new customer has been created: " + newCustomer);
        return newCustomerDTO;
    }

    private void checkIfCustomerAlreadyExits(Customer newCustomer) {
        if (getAllCustomers().contains(newCustomer)){
            throw new IllegalArgumentException(newCustomer + " already exists.");
        }
    }

    private List<Customer> getAllCustomers() {
        return userRepository.getAllCustomers();
    }

    public List<CustomerDTO> viewAllCustomers() {
        List<Customer> customers = this.getAllCustomers();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : customers) {
            customerDTOS.add(getCustomerDTO(customer));
        }
        return customerDTOS;
    }

    private CustomerDTO getCustomerDTO(Customer customer) {
        return this.customerMapper.customerToCustomerDTO(customer);
    }


    public CustomerDTO getCustomerByUserName(String userName) {
        Customer customer = this.userRepository.getCustomerByUserName(userName);
        return getCustomerDTO(customer);
    }



}
