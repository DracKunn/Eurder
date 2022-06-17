package com.switchfully.eurder.users.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("customers")
public class CustomerController {
    private final java.util.logging.Logger logger = Logger.getLogger(this.getClass().getName());
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO registerNewCustomer(@RequestBody CustomerDTO customerDTO) {

        logger.info("attempting to register a member");
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

    @GetMapping(path = "/{userName}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO viewCustomer(@PathVariable String userName){
        return this.customerService.getCustomerByUserName(userName);
    }

}
