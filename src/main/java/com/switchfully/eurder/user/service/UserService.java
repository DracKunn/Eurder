package com.switchfully.eurder.user.service;

import com.switchfully.eurder.user.api.dto.CreateUserDTO;
import com.switchfully.eurder.user.api.dto.UserDTO;
import com.switchfully.eurder.user.domain.Role;
import com.switchfully.eurder.user.domain.User;
import com.switchfully.eurder.user.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.switchfully.eurder.util.validation.ValidatorsUtility.isNotNull;


@Service
@AllArgsConstructor
public class UserService {
    private final Logger customerServiceLogger = Logger.getLogger(this.getClass().getName());

    private UserMapper userMapper;
    private UserRepository userRepository;

    public UserDTO registerNewCustomer(CreateUserDTO newCustomerDTO) throws IllegalArgumentException {
        isNotNull(newCustomerDTO, "new customer DTO");
        User newCustomer = this.userMapper.toEntity(newCustomerDTO);
        checkIfCustomerAlreadyExits(newCustomer);
        this.userRepository.save(newCustomer);
        customerServiceLogger.info("A new customer has been created: " + newCustomer);
        return userMapper.toDTO(newCustomer);
    }

    private void checkIfCustomerAlreadyExits(User newCustomer) {
        if (getAllCustomers().contains(newCustomer)) {
            throw new IllegalArgumentException(newCustomer + " already exists.");
        }
    }

    private List<User> getAllCustomers() {
        return userRepository.findAll().stream().filter(user -> user.getUserRole() == Role.CUSTOMER).toList();
    }

    public List<UserDTO> viewAllCustomers() {
        List<User> customers = this.getAllCustomers();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User customer : customers) {
            userDTOS.add(getCustomerDTO(customer));
        }
        return userDTOS;
    }

    private UserDTO getCustomerDTO(User customer) {
        return this.userMapper.toDTO(customer);
    }


    private User getCustomerById(int id) {
        User foundCustomer = this.userRepository.findById(id).orElse(null);
        if (foundCustomer == null) {
            throw new IllegalArgumentException("Customer not found");
        }
        return foundCustomer;
    }


    public UserDTO getCustomerDTOById(int id) {
        return getCustomerDTO(getCustomerById(id));
    }


}
