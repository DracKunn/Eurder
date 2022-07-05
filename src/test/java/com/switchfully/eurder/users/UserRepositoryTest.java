package com.switchfully.eurder.users;

import com.switchfully.eurder.users.admin.domain.Admin;
import com.switchfully.eurder.users.user.domain.UserRepository;
import com.switchfully.eurder.users.customer.domain.Customer;
import com.switchfully.eurder.util.address.Address;
import com.switchfully.eurder.util.name.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    @Test
    @DisplayName("given A User And A UserRepository when Adding USer To Repository then User is in repository")
    void givenAUserAndAUserRepositoryWhenAddingUSerToRepositoryThenUserIsInRepository() {

        //given
        String username = "bruenor";
        Name name = new Name("Bruenor", "The Bard");
        String email = "bruenor@bardcollege.org";
        Address address = new Address("streetName", 666, "postalCode", "city");
        String phoneNumber = "+32444555666";
        Customer customer = new Customer(username,name, email, address, phoneNumber);

        UserRepository userRepository = new UserRepository();
        //when
        Customer actual = userRepository.addNewCustomer(customer);
        //then
        Customer expected = userRepository.getCustomerByUserName("bruenor");
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("given a user repository then admin is in repository")
    void givendAUserRepositoryThenAdminIsInRepository() {

 //given

        UserRepository userRepository = new UserRepository();

 //when
 //then
        assertEquals(new Admin("B-rex",new Name("Brecht", "Van Rie"), "brecht.vanrie@gmail.com"),userRepository.getAdminByUserName("B-rex"));

    }



}
