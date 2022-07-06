package com.switchfully.eurder.user.api;

import com.switchfully.eurder.user.api.dto.customer.CreateCustomerDTO;
import com.switchfully.eurder.user.api.dto.customer.CustomerDTO;
import com.switchfully.eurder.user.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("customers")
@AllArgsConstructor
public class CustomerController {
    private final java.util.logging.Logger customerControllerLogger = Logger.getLogger(this.getClass().getName());
    private CustomerService customerService;


    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO registerNewCustomer(@RequestBody CreateCustomerDTO createCustomerDTO) {

        String login = createCustomerDTO.email();
        customerControllerLogger.info("New customer with login: " + login + " registered.");
        return this.customerService.registerNewCustomer(createCustomerDTO);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> viewAllCustomers() {
        customerControllerLogger.info("Displaying all known customers: ");
        return this.customerService.viewAllCustomers();
    }

    @GetMapping(path = "/{userId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO viewCustomer(@PathVariable int userId){
        return this.customerService.getCustomerDTOById(userId);
    }

}
