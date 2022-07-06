package com.switchfully.eurder.user.api;

import com.switchfully.eurder.user.api.dto.CreateUserDTO;
import com.switchfully.eurder.user.api.dto.UserDTO;
import com.switchfully.eurder.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("customers")
@AllArgsConstructor
public class UserController {
    private final java.util.logging.Logger customerControllerLogger = Logger.getLogger(this.getClass().getName());
    private UserService userService;


    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO registerNewCustomer(@RequestBody CreateUserDTO createUserDTO) {

        String login = createUserDTO.email();
        customerControllerLogger.info("New customer with login: " + login + " registered.");
        return this.userService.registerNewCustomer(createUserDTO);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> viewAllCustomers() {
        customerControllerLogger.info("Displaying all known customers: ");
        return this.userService.viewAllCustomers();
    }

    @GetMapping(path = "/{userId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO viewCustomer(@PathVariable int userId){
        return this.userService.getCustomerDTOById(userId);
    }

}
