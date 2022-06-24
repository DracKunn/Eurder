package com.switchfully.eurder.users.customer;

import com.switchfully.eurder.users.Address;
import com.switchfully.eurder.users.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    String userName = "bruenor";
    String firstName = "Bruenor";
    String lastName = "The Bard";
    Name name = new Name(firstName, lastName);
    String email = "bruenor@bardcollege.org";
    Address address = new Address("streetName", 666, "postalCode", "city");
    String phoneNumber = "+32444555666";

    @Test
    @DisplayName("given a user with an email adress when email is valid then return email")
    void givenAUserWithAnEmailAdress_whenEmailIsValid_thenReturnEmail() {
        //given
        Customer customer = new Customer(userName, name, email, address, phoneNumber);
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

        String email = "bruenorbardcollegeorg";

        //When

        //then
        assertThrows(IllegalArgumentException.class, () -> new Customer(userName, name, email, address, phoneNumber));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given a null or blank email address when creating a customer then throws an exception")
    void givenANullOrBlankEmailAddress_whenCreatingACustomer_thenThrowsAnException(String testEmail) {


        //then
        assertThrows(IllegalArgumentException.class, () -> new Customer(userName, name, testEmail, address, phoneNumber));
    }

    @Test
    @DisplayName("given a user when getName then return name ")
    void givenAUserWhenGetNameThenReturnName() {
        //given
        Customer customer = new Customer(userName, name, email, address, phoneNumber);

        //when
        String actual = customer.getName().toString();
        //then
        assertEquals("Bruenor The Bard", actual);
    }

    @Test
    @DisplayName("given a correct name when creating the name then everything is ok")
    void givenACustomerWithACorrectNameWhenCreatingTheCustomerThenEverythingIsOk() {

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
        Name name = new Name(testName, lastName);

        //then
        assertThrows(IllegalArgumentException.class, () -> new Customer(userName, name, email, address, phoneNumber));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given a blank or null last name then throws illegal argument Exception")
    void givenABlankOrNullLastNameThenThrowsIllegalArgumentException(String testName) {
        //given

        Name name = new Name(firstName, testName);

        //then
        assertThrows(IllegalArgumentException.class, () -> new Customer(userName, name, email, address, phoneNumber));
    }

    @Test
    @DisplayName("given a user with a correct phone number when checking number then no exception is thrown")
    void givenAUserWithACorrectPhoneNumberWhenCheckingNumberThenNoExceptionIsThrown() {

        //given
        Customer customer = new Customer(userName, name, email, address, phoneNumber);
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
        String phoneNumber = "+324445";
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new Customer(userName, name, email, address, phoneNumber));
    }

    @ParameterizedTest
    @DisplayName("given a blank or null phone number when creating a customer then throws illegal argument exception")
    @NullAndEmptySource
    void givenABlankOrNullPhoneNumberWhenCreatingACustomerThenThrowsIllegalArgumentException(String badNumber) {


        //then
        assertThrows(IllegalArgumentException.class, () -> new Customer(userName, name, email, address, badNumber));
    }

    @Test
    @DisplayName("given a correct address when creating customer then no exception is thrown")
    void givenACorrectAddressWhenCreatingCustomerThenNoExceptionIsThrown() {

        //when
        Customer customer = new Customer(userName, name, email, address, phoneNumber);
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
        Address address = new Address(badStreetName, 666, "postalCode", "city");

        //then
        assertThrows(IllegalArgumentException.class, () -> new Customer(userName, name, email, address, phoneNumber));

    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given a null or empty psotalCode when creating customer throws Illegal Argument exception")
    void givenANullOrEmptyPosatalCodeWhenCreatingCustomerThrowsIllegalArgumentException(String badPostalCode) {

        //given
        Address address = new Address("streetName", 666, badPostalCode, "city");

        //then
        assertThrows(IllegalArgumentException.class, () -> new Customer(userName, name, email, address, phoneNumber));

    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given a null or empty city when creating customer throws Illegal Argument exception")
    void givenANullOrEmptyCityWhenCreatingCustomerThrowsIllegalArgumentException(String badCity) {

        //given
        Address address = new Address("streetName", 666, "postalCode", badCity);
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new Customer(userName, name, email, address, phoneNumber));

    }

    @Test
    @DisplayName("given a bad username when creating a user then throw exception")
    void givenABadUsernameWhenCreatingAUserThenThrowException() {

        //given
        String username = "Bruenor the terrible url";
        //when /then
        assertThrows(IllegalArgumentException.class, () -> new Customer(username, name, email, address, phoneNumber));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given a blank or null username when creating a user then throw an exception")
    void givenABlankOrNullUsernameWhenCreatingAUserThenThrowAnException(String badUsername) {


        //then
        assertThrows(IllegalArgumentException.class, () -> new Customer(badUsername, name, email, address, phoneNumber));
    }


}
