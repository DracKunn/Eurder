package com.switchfully.eurder.users.customer;

import com.switchfully.eurder.users.Address;
import com.switchfully.eurder.users.Name;
import com.switchfully.eurder.users.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    @Test
    @DisplayName("given a couple of customers, when asking all customers, then get a list of customer DTOs")
    void givenACoupleOfCustomersWhenAskingAllCustomersThenGetAListOfCustomerDtOs() {

        //given
        Address address = new Address("Volderstraat", 12, "9000", "Gent");
        Customer sarah = new Customer("sarah",new Name("Sarah", "Pout"), "sarah.pout@hotmail.com", address, "+32475693215");
        Customer waldo = new Customer("waldo",new Name("Waldo", "Where"), "where.waldo@bing.com", address, "+3492583674");
        Customer henry = new Customer("henry",new Name("Henry", "Oak"), "henry.oak@yahoo.com", address, "+19159969739");

        UserRepository userRepository = new UserRepository();
        CustomerMapper customerMapper = new CustomerMapper();
        CustomerService customerService = new CustomerService(customerMapper, userRepository);

        CustomerDTO sarahDTO = customerMapper.customerToCustomerDTO(sarah);
        CustomerDTO waldoDTO = customerMapper.customerToCustomerDTO(waldo);
        CustomerDTO henryDTO = customerMapper.customerToCustomerDTO(henry);
        customerService.registerNewCustomer(sarahDTO);
        customerService.registerNewCustomer(waldoDTO);
        customerService.registerNewCustomer(henryDTO);

        //when
        List<CustomerDTO> actual = customerService.viewAllCustomers();

        //then
        List<CustomerDTO> expected = List.of(sarahDTO, waldoDTO, henryDTO);
        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("given a couple of customers, when asking a specific customer by email, then get that customer")
    void givenACoupleOfCustomersWhenAskingASpecificCustomerByEmail_thenGetThatCustomer() {

        //given
        String waldoEmail = "where.waldo@bing.com";
        Address address = new Address("Volderstraat", 12, "9000", "Gent");
        Customer sarah = new Customer("sarah",new Name("Sarah", "Pout"), "sarah.pout@hotmail.com", address, "+32475693215");
        Customer waldo = new Customer("waldo",new Name("Waldo", "Where"), waldoEmail, address, "+3492583674");
        Customer henry = new Customer("henry",new Name("Henry", "Oak"), "henry.oak@yahoo.com", address, "+19159969739");

        UserRepository userRepository = new UserRepository();
        CustomerMapper customerMapper = new CustomerMapper();
        CustomerService customerService = new CustomerService(customerMapper, userRepository);

        CustomerDTO sarahDTO = customerMapper.customerToCustomerDTO(sarah);
        CustomerDTO waldoDTO = customerMapper.customerToCustomerDTO(waldo);
        CustomerDTO henryDTO = customerMapper.customerToCustomerDTO(henry);
        customerService.registerNewCustomer(sarahDTO);
        customerService.registerNewCustomer(waldoDTO);
        customerService.registerNewCustomer(henryDTO);

        //when
        CustomerDTO actual = customerService.getCustomerByEmail(waldoEmail);

        //then

        assertEquals(waldoDTO, actual);

    }

    @Test
    @DisplayName("given a customer service , when trying to register a customer as null, then throw an illegal argument exception")
    void givenACustomerServiceWhenTryingToRegisterACustomerAsNullThenThrowAnIllegalArgumentException() {

        //given
        UserRepository userRepository = new UserRepository();
        CustomerMapper customerMapper = new CustomerMapper();
        CustomerService customerService = new CustomerService(customerMapper, userRepository);
        //when/then
        assertThrows(IllegalArgumentException.class, ()-> customerService.registerNewCustomer(null));

    }

}
