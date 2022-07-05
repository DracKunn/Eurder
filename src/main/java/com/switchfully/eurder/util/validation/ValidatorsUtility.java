package com.switchfully.eurder.util.validation;

import com.switchfully.eurder.orders.domain.Order;
import com.switchfully.eurder.util.address.Address;
import com.switchfully.eurder.util.name.Name;
import com.switchfully.eurder.users.customer.domain.Customer;
import com.switchfully.eurder.users.customer.api.dto.CustomerDTO;

import java.nio.file.AccessDeniedException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorsUtility {
    public static final String LEGAL_URL_ADDRESS_CHARACTERS = "^(?!-)[A-Za-z0-9-]{1,63}(?<!-)$";
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

    public static void isNotNull(Customer customerToValidate, String variableFieldName) {
        if (customerToValidate == null) {
            throw new IllegalArgumentException(variableFieldName + " cannot be empty");
        }
    }

    public static String validateURLFriendly(String string) throws IllegalArgumentException {
        isNotNullOrEmpty(string, "order ID");
        Pattern pattern = Pattern.compile(LEGAL_URL_ADDRESS_CHARACTERS);
        Matcher matcher = pattern.matcher(string);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("field cannot contain a space or URL unfriendly characters.");
        }
        return string;
    }

    public static String validateEmail(String email) throws IllegalArgumentException {
        isNotNullOrEmpty(email, "email");
        Pattern pattern = Pattern.compile(OWASP_EMAIL_VALIDATION);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("invalid e-mail format");
        }
        return email;
    }

    public static Name validateName(Name name) throws IllegalArgumentException {
        areNotNullOrEmpty(name.getFirstName(), name.getLastName());
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
        areNotNullOrEmpty(address.getStreetName(), address.getPostalCode(), address.getCity());
        if (address.getStreetNumber() <= 0) {
            throw new IllegalArgumentException("street number must be greater than 0");
        }
        return address;
    }

    public static void validateCustomerHasThisOrder(Customer customer, Order order) throws AccessDeniedException {
        if (!order.getCustomer().equals(customer)){
            throw new AccessDeniedException("this user cannot view this order");
        }
    }

    public static int isNotNegative(int integerNumber, String integerName){
        if (integerNumber <0){
            throw new IllegalArgumentException(integerName + " cannot be negative");
        }
        return integerNumber;
    }
    public static double isNotNegative(double doubleNumber, String doubleName){
        if (doubleNumber <0){
            throw new IllegalArgumentException(doubleName + " cannot be negative");
        }
        return doubleNumber;
    }
}
