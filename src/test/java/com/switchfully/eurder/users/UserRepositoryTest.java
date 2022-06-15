package com.switchfully.eurder.users;

import com.switchfully.eurder.users.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRepositoryTest {
    @Test
    @DisplayName("given A User And A UserRepository when Adding USer To Repository then User is in repository")
    void givenAUserAndAUserRepositoryWhenAddingUSerToRepositoryThenUserIsInRepository() {

        //given
        Name name = new Name("Bruenor", "The Bard");
        String email = "bruenor@bardcollege.org";
        Address address = new Address("streetName", 666, "postalCode", "city");
        String phoneNumber = "+32444555666";
        Customer customer = new Customer(name, email, address, phoneNumber);

        UserRepository userRepository = new UserRepository();
        //when
        Customer actual = userRepository.addNewUser(customer);
        //then
        Customer expected = (Customer) userRepository.getUserByEmail("bruenor@bardcollege.org");
        assertEquals(expected,actual);
    }

}
