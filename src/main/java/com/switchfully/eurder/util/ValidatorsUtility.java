package com.switchfully.eurder.util;

import com.switchfully.eurder.users.Address;
import com.switchfully.eurder.users.Name;
import com.switchfully.eurder.users.customer.CustomerDTO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorsUtility {
    public static final String OWASP_SPACE_VALIDATION = "'\s'";
    public static final String OWASP_EMAIL_VALIDATION = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public static final String OWASP_PHONE_VALIDATION = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$" + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$";


    private ValidatorsUtility() {
        throw new IllegalStateException("Utility class");
    }

    public static void isNotNullOrEmpty(String stringToValidate, String variableFieldName) {
        if (stringToValidate == null || stringToValidate.isBlank()) {
            throw new IllegalArgumentException(variableFieldName + " cannot be empty");
        }
    }

    public static void areNotNullOrEmpty(String... stringsToValidate) {
        for (String stringToValidate : stringsToValidate) {
            if (stringToValidate == null || stringToValidate.isBlank()) {
                throw new IllegalArgumentException("field cannot be empty");
            }
        }
    }

    public static void isNotNull(CustomerDTO customerDTOToValidate, String variableFieldName) {
        if (customerDTOToValidate == null) {
            throw new IllegalArgumentException(variableFieldName + " cannot be empty");
        }
    }

    public static String validateStringNoSpace(String string) throws IllegalArgumentException{
        isNotNullOrEmpty(string, "order ID");
        Pattern pattern = Pattern.compile(OWASP_SPACE_VALIDATION);
        Matcher matcher = pattern.matcher(string);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("field cannot contain a space");
        }
        return string;
    }

    public static String validateEmail(String email) throws IllegalArgumentException{
        isNotNullOrEmpty(email, "email");
        Pattern pattern = Pattern.compile(OWASP_EMAIL_VALIDATION);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("invalid e-mail format");
        }
        return email;
    }

    public static Name validateName(Name name) throws IllegalArgumentException {
        areNotNullOrEmpty(name.firstName(), name.firstName());
        return name;
    }

    //  Phone-number has to be of one of the following formats: 2055550125, 202 555 0125, (202) 555-0125, +111 (202) 555-0125,
    //      636 856 789, +111 636 856 789, 636 85 67 89, +111 636 85 67 89
    public static String validatePhoneNumber(String phoneNumber) throws IllegalArgumentException {
        isNotNullOrEmpty(phoneNumber, "phone number");
        Pattern pattern = Pattern.compile(OWASP_PHONE_VALIDATION);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid phone-number format");
        }
        return phoneNumber;
    }

    public static Address validateAddress(Address address) throws IllegalArgumentException {
        areNotNullOrEmpty(address.streetName(), address.postalCode(), address.city());
        if (address.streetNumber() <= 0){
            throw new IllegalArgumentException("street number must be greater than 0");
        }
        return address;
    }
}
