package com.switchfully.eurder.users.customer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class CustomerController {
    CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public CustomerDTO registerNewCustomer(@RequestBody CustomerDTO customerDTO){
        this.customerService.registerNewCustomer(customerDTO);
        return customerDTO;
    }

}
