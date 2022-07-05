package com.switchfully.eurder.user.service.customer;

import com.switchfully.eurder.user.domain.User;
import com.switchfully.eurder.user.domain.UserRepository;
import com.switchfully.eurder.user.api.dto.customer.CustomerDTO;
import com.switchfully.eurder.user.domain.customer.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static com.switchfully.eurder.util.validation.ValidatorsUtility.isNotNull;


@Service
@AllArgsConstructor
public class CustomerService {
    private final Logger customerServiceLogger = Logger.getLogger(this.getClass().getName());

    public CustomerMapper customerMapper;
    private UserRepository userRepository;

    public CustomerDTO registerNewCustomer(CustomerDTO newCustomerDTO) throws IllegalArgumentException {
        isNotNull(newCustomerDTO, "new customer DTO");
        Customer newCustomer = this.customerMapper.toEntity(newCustomerDTO);
        checkIfCustomerAlreadyExits(newCustomer);
        this.userRepository.save(newCustomer);
        customerServiceLogger.info("A new customer has been created: " + newCustomer);
        return newCustomerDTO;
    }

    private void checkIfCustomerAlreadyExits(Customer newCustomer) {
        if (getAllCustomers().contains(newCustomer)) {
            throw new IllegalArgumentException(newCustomer + " already exists.");
        }
    }

    private List<Customer> getAllCustomers() {
        return Collections.singletonList((Customer) userRepository.findAll().stream().filter(user -> user.getClass() == Customer.class).toList());
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
        return this.customerMapper.toDTO(customer);
    }


    private Customer getCustomerById(int id) {
        Customer foundCustomer = (Customer) this.userRepository.findById(id).orElse(null);
        if (foundCustomer == null) {
            throw new IllegalArgumentException("Customer not found");
        }
        return foundCustomer;
    }


    public CustomerDTO getCustomerDTOById(int id) {
        return getCustomerDTO(getCustomerById(id));
    }


}
