package com.switchfully.eurder.users.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private final java.util.logging.Logger logger = Logger.getLogger(this.getClass().getName());
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO registerNewCustomer(@RequestBody CustomerDTO customerDTO) {
        String login = customerDTO.getEmail();
        logger.info("New customer with login: " + login + " registered.");
        return this.customerService.registerNewCustomer(customerDTO);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> viewAllCustomers() {
        logger.info("Displaying all known customers: ");
        return this.customerService.viewAllCustomers();
    }

    @GetMapping(path = "/{email}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO viewCustomer(@PathVariable String email){
        return this.customerService.getCustomerByEmail(email);
    }

}
