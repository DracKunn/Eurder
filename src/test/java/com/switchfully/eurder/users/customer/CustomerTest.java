package com.switchfully.eurder.users.customer;

import com.switchfully.eurder.users.Address;
import com.switchfully.eurder.users.Name;
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

    @Test
    @DisplayName("given a user when getName then return name ")
    void givenAUserWhenGetNameThenReturnName() {
        //given
        Name name = new Name("Bruenor", "The Bard");
        String email = "bruenor@bardcollege.org";
        Address address = new Address("streetName", 666, "postalCode", "city");
        String phoneNumber = "+32444555666";
        Customer customer = new Customer(name, email, address, phoneNumber);

        //when
        String actual = customer.getName().toString();
        //then
        assertEquals("Bruenor The Bard", actual);
    }

    @Test
    @DisplayName("given a correct name when creating the name then everything is ok")
    void givenACustomerWithACorrectNameWhenCreatingTheCustomerThenEverythingIsOk() {

        //given
        Name name = new Name("Bruenor", "The Bard");
        //When
        String actual = name.toString();
        //then
        String expected = "Bruenor The Bard";
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given a blank or null first name then throws illegal argument Exception")
    void givenABlankOrNullFirstNameThenThrowsIllegalArgumentException(String testName) {
        //given
        String lastName = "The Bard";
        Name name = new Name(testName, lastName);
        String email = "bruenor@bardcollege.org";
        Address address = new Address("streetName", 666, "postalCode", "city");
        String phoneNumber = "+32444555666";
        //then
        assertThrows(IllegalArgumentException.class, () -> new Customer(name, email, address, phoneNumber));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given a blank or null last name then throws illegal argument Exception")
    void givenABlankOrNullLastNameThenThrowsIllegalArgumentException(String testName) {
        //given
        String firstName = "Bruenor";
        Name name = new Name(firstName, testName);
        String email = "bruenor@bardcollege.org";
        Address address = new Address("streetName", 666, "postalCode", "city");
        String phoneNumber = "+32444555666";
        //then
        assertThrows(IllegalArgumentException.class, () -> new Customer(name, email, address, phoneNumber));
    }

    @Test
    @DisplayName("given a user with a correct phone number when checking number then no exception is thrown")
    void givenAUserWithACorrectPhoneNumberWhenCheckingNumberThenNoExceptionIsThrown() {

        //given
        Name name = new Name("Bruenor", "The Bard");
        String email = "bruenor@bardcollege.org";
        Address address = new Address("streetName", 666, "postalCode", "city");
        String phoneNumber = "+32444555666";
        Customer customer = new Customer(name, email, address, phoneNumber);
        //when
        String actual = customer.getPhoneNumber();
        //then
        String expected = "+32444555666";
        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("given an incomplete phone number when creating customer then throws illegal argumetn exception")
    void givenAnIncompletePhoneNumberWhenCreatingCustomerThenThrowsIllegalArgumetnException() {

        //given
        Name name = new Name("Bruenor", "The Bard");
        String email = "bruenor@bardcollege.org";
        Address address = new Address("streetName", 666, "postalCode", "city");
        String phoneNumber = "+324445";
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new Customer(name, email, address, phoneNumber));
    }

    @ParameterizedTest
    @DisplayName("given a blank or null phone number when creating a customer then throws illegal argument exception")
    @NullAndEmptySource
    void givenABlankOrNullPhoneNumberWhenCreatingACustomerThenThrowsIllegalArgumentException(String badNumber) {

        //given
        Name name = new Name("Bruenor", "The Bard");
        String email = "bruenor@bardcollege.org";
        Address address = new Address("streetName", 666, "postalCode", "city");

        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new Customer(name, email, address, badNumber));
    }

    @Test
    @DisplayName("given a correct address when creating customer then no exception is thrown")
    void givenACorrectAddressWhenCreatingCustomerThenNoExceptionIsThrown() {

        //given
        Name name = new Name("Bruenor", "The Bard");
        String email = "bruenor@bardcollege.org";
        Address address = new Address("streetName", 666, "postalCode", "city");
        String phoneNumber = "+32444555666";
        Customer customer = new Customer(name, email, address, phoneNumber);
        //when
        String actual = customer.getAddress().toString();

        //then
        String expected = "streetName 666, postalCode city";
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given a null or empty streetName when creating customer throws Illegal Argument exception")
    void givenANullOrEmptyStreetNameWhenCreatingCustomerThrowsIllegalArgumentException(String badStreetName) {

        //given
        Name name = new Name("Bruenor", "The Bard");
        String email = "bruenor@bardcollege.org";
        Address address = new Address(badStreetName, 666, "postalCode", "city");
        String phoneNumber = "+32444555666";
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new Customer(name, email, address, phoneNumber));

    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given a null or empty psotalCode when creating customer throws Illegal Argument exception")
    void givenANullOrEmptyPosatalCodeWhenCreatingCustomerThrowsIllegalArgumentException(String badPostalCode) {

        //given
        Name name = new Name("Bruenor", "The Bard");
        String email = "bruenor@bardcollege.org";
        Address address = new Address("streetName", 666, badPostalCode, "city");
        String phoneNumber = "+32444555666";
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new Customer(name, email, address, phoneNumber));

    }
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given a null or empty city when creating customer throws Illegal Argument exception")
    void givenANullOrEmptyCityWhenCreatingCustomerThrowsIllegalArgumentException(String badCity) {

        //given
        Name name = new Name("Bruenor", "The Bard");
        String email = "bruenor@bardcollege.org";
        Address address = new Address("streetName", 666, "postalCode", badCity);
        String phoneNumber = "+32444555666";
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new Customer(name, email, address, phoneNumber));

    }


}
