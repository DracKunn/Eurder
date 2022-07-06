package com.switchfully.eurder.util.validation;

import com.switchfully.eurder.order.domain.Order;
import com.switchfully.eurder.user.domain.User;
import com.switchfully.eurder.util.address.domain.Address;
import com.switchfully.eurder.util.name.domain.Name;

import java.nio.file.AccessDeniedException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorsUtility {
    public static final Logger validatorLogger = Logger.getLogger(ValidatorsUtility.class.getName());

    public static final String LEGAL_URL_ADDRESS_CHARACTERS = "^(?!-)[A-Za-z0-9-]{1,63}(?<!-)$";
    public static final String OWASP_EMAIL_VALIDATION = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public static final String OWASP_PHONE_VALIDATION = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$" + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$";

    private ValidatorsUtility() {
        throw new IllegalStateException("Utility class");
    }

    public static void isNotNullOrEmpty(String stringToValidate, String variableFieldName) {
        if (stringToValidate == null || stringToValidate.isBlank()) {
            String errorMessage = variableFieldName + " cannot be empty";
            validatorLogger.warning(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void areNotNullOrEmpty(String... stringsToValidate) {
        for (String stringToValidate : stringsToValidate) {
            if (stringToValidate == null || stringToValidate.isBlank()) {
                String errorMessage = "field cannot be empty";
                validatorLogger.warning(errorMessage);
                throw new IllegalArgumentException(errorMessage);
            }
        }
    }

    public static void isNotNull(Object objectToValidate, String variableFieldName) {
        if (objectToValidate == null) {
            String errorMessage = variableFieldName + " cannot be empty";
            validatorLogger.warning(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static String validateURLFriendly(String string) throws IllegalArgumentException {
        isNotNullOrEmpty(string, "order ID");
        Pattern pattern = Pattern.compile(LEGAL_URL_ADDRESS_CHARACTERS);
        Matcher matcher = pattern.matcher(string);
        if (!matcher.matches()) {
            String errorMessage = "field cannot contain a space or URL unfriendly characters.";
            validatorLogger.warning(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return string;
    }

    public static String validateEmail(String email) throws IllegalArgumentException {
        isNotNullOrEmpty(email, "email");
        Pattern pattern = Pattern.compile(OWASP_EMAIL_VALIDATION);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            String errorMessage = "invalid e-mail format";
            validatorLogger.warning(errorMessage);
            throw new IllegalArgumentException(errorMessage);
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
            String errorMessage = "Invalid phone-number format";
            validatorLogger.warning(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return phoneNumber;
    }

    public static Address validateAddress(Address address) throws IllegalArgumentException {
        areNotNullOrEmpty(address.getStreetName(), address.getPostalCode(), address.getCity());
        if (address.getStreetNumber() <= 0) {
            String errorMessage = "street number must be greater than 0";
            validatorLogger.warning(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return address;
    }

    public static void validateCustomerHasThisOrder(User customer, Order order) throws AccessDeniedException {
        if (!order.getCustomer().equals(customer)) {
            String errorMessage = "this user cannot view this order";
            validatorLogger.warning(errorMessage);
            throw new AccessDeniedException(errorMessage);
        }
    }

    public static int isNotNegative(int integerNumber, String integerName) {
        if (integerNumber < 0) {
            String errorMessage = integerName + " cannot be negative";
            validatorLogger.warning(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return integerNumber;
    }

    public static double isNotNegative(double doubleNumber, String doubleName) {
        if (doubleNumber < 0) {
            String errorMessage = doubleName + " cannot be negative";
            validatorLogger.warning(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return doubleNumber;
    }
}
