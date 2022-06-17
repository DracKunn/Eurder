package com.switchfully.eurder.users.customer;

import com.switchfully.eurder.users.Address;
import com.switchfully.eurder.users.Name;
import com.switchfully.eurder.users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    static Address address = new Address("Volderstraat", 12, "9000", "Gent");
    static Customer sarah = new Customer("sarah", new Name("Sarah", "Pout"), "sarah.pout@hotmail.com", address, "+32475693215");
    static Customer waldo = new Customer("waldo", new Name("Waldo", "Where"), "where.waldo@bing.com", address, "+3492583674");
    static Customer henry = new Customer("henry", new Name("Henry", "Oak"), "henry.oak@yahoo.com", address, "+19159969739");

    static UserRepository userRepository = new UserRepository();
    static CustomerMapper customerMapper = new CustomerMapper();
    static CustomerService customerService = new CustomerService(customerMapper, userRepository);

    static CustomerDTO sarahDTO = customerMapper.customerToCustomerDTO(sarah);
    static CustomerDTO waldoDTO = customerMapper.customerToCustomerDTO(waldo);
    static CustomerDTO henryDTO = customerMapper.customerToCustomerDTO(henry);

    @BeforeEach
    void setUp() {
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
