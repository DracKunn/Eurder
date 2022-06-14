package com.switchfully.eurder.users.customer;

import com.switchfully.eurder.users.Address;
import com.switchfully.eurder.users.Name;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    @Test
    @DisplayName("given a user with an email adress when email is valid then return email")
    void givenAUserWithAnEmailAdress_whenEmailIsValid_thenReturnEmail() {
        //given
        Name name = new Name("Bruenor", "The Bard");
        String email = "bruenor@bardcollege.org";
        Address address = new Address("streetName", 666, "postalCode", "city");
        String phoneNumber = "+32444555666";
        Customer customer = new Customer(name, email, address, phoneNumber);
        //When
        String actual = customer.getEmail();
        //then
        String expected = "bruenor@bardcollege.org";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("given a customer with a bad email format when creating the customer then throws an exception")
    void givenACustomerWithABadEmailFormatWhenCreatingTheCustomerThenThrowsAnException() {
        //given
        Name name = new Name("Bruenor", "The Bard");
        String email = "bruenorbardcollegeorg";
        Address address = new Address("streetName", 666, "postalCode", "city");
        String phoneNumber = "+32444555666";

        //When

        //then
        assertThrows(IllegalArgumentException.class, () -> new Customer(name, email, address, phoneNumber));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given a null or blank email address when creating a customer then throws an exception")
    void givenANullOrBlankEmailAddress_whenCreatingACustomer_thenThrowsAnException(String testEmail) {

        //given
        Name name = new Name("Bruenor", "The Bard");
        Address address = new Address("streetName", 666, "postalCode", "city");
        String phoneNumber = "+32444555666";

        //then
        assertThrows(IllegalArgumentException.class, () -> new Customer(name, testEmail, address, phoneNumber));
    }


}
