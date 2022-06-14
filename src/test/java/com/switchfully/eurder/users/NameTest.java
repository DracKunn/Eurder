package com.switchfully.eurder.users;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {



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
    @DisplayName("given a blank or null first or last name then throws illegal argument Exception")
    void givenABlankOrNullFirstOrLastNameThenThrowsIllegalArgumentException(String testName) {
        //given
        String firstName = "Bruenor";
        String lastName = "The Bard";
        //then
        assertThrows(IllegalArgumentException.class, () -> new Name(testName, lastName));
        assertThrows(IllegalArgumentException.class, () -> new Name(firstName, testName));
    }



}

