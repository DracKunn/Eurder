package com.switchfully.eurder.users.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping()
    public List<CustomerDTO> viewAllCustomers(){
        return this.customerService.viewAllCustomers();
    }

}
