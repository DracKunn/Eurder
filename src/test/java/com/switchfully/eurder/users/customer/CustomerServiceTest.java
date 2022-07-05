package com.switchfully.eurder.users.customer;

import com.switchfully.eurder.util.address.Address;
import com.switchfully.eurder.util.name.Name;
import com.switchfully.eurder.users.user.domain.UserRepository;
import com.switchfully.eurder.users.customer.api.dto.CustomerDTO;
import com.switchfully.eurder.users.customer.domain.Customer;
import com.switchfully.eurder.users.customer.service.CustomerMapper;
import com.switchfully.eurder.users.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
     Address address = new Address("Volderstraat", 12, "9000", "Gent");
     Customer sarah = new Customer("sarah", new Name("Sarah", "Pout"), "sarah.pout@hotmail.com", address, "+32475693215");
     Customer waldo = new Customer("waldo", new Name("Waldo", "Where"), "where.waldo@bing.com", address, "+3492583674");
     Customer henry = new Customer("henry", new Name("Henry", "Oak"), "henry.oak@yahoo.com", address, "+19159969739");

     UserRepository userRepository;
     CustomerMapper customerMapper = new CustomerMapper();
     CustomerService customerService;

     CustomerDTO sarahDTO = customerMapper.customerToCustomerDTO(sarah);
     CustomerDTO waldoDTO = customerMapper.customerToCustomerDTO(waldo);
     CustomerDTO henryDTO = customerMapper.customerToCustomerDTO(henry);

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        customerService = new CustomerService(customerMapper, userRepository);
        customerService.registerNewCustomer(sarahDTO);
        customerService.registerNewCustomer(waldoDTO);
        customerService.registerNewCustomer(henryDTO);
    }


    @Test
    @DisplayName("given a couple of customers, when asking all customers, then get a list of customer DTOs")
    void givenACoupleOfCustomersWhenAskingAllCustomersThenGetAListOfCustomerDtOs() {

        //given


        //when
        List<CustomerDTO> actual = customerService.viewAllCustomers();

        //then
        List<CustomerDTO> expected = List.of(henryDTO, waldoDTO, sarahDTO);
        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("given a couple of customers, when asking a specific customer by user name, then get that customer")
    void givenACoupleOfCustomersWhenAskingASpecificCustomerByEmail_thenGetThatCustomer() {

        //given


        //when
        CustomerDTO actual = customerService.getCustomerByUserName("waldo");

        //then

        assertEquals(waldoDTO, actual);

    }

    @Test
    @DisplayName("given a customer service , when trying to register a customer as null, then throw an illegal argument exception")
    void givenACustomerServiceWhenTryingToRegisterACustomerAsNullThenThrowAnIllegalArgumentException() {

        //given

        //when/then
        assertThrows(IllegalArgumentException.class, () -> customerService.registerNewCustomer(null));

    }

}
