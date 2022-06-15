package com.switchfully.eurder.users.customer;

import com.switchfully.eurder.users.Address;
import com.switchfully.eurder.users.Name;
import com.switchfully.eurder.users.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer extends User {
    public static final String PHONE_VALIDATION =
            "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$";
    private final Address address;
    private final String phoneNumber;

    public Customer(Name name, String email, Address address, String phoneNumber) {
        super(name, email);
        this.address = validateAddress(address);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    //  Phone-number has to be of one of the following formats: 2055550125, 202 555 0125, (202) 555-0125, +111 (202) 555-0125,
    //      636 856 789, +111 636 856 789, 636 85 67 89, +111 636 85 67 89
    private static String validatePhoneNumber(String phoneNumber) throws IllegalArgumentException {
        isNotNullOrEmpty(phoneNumber,"phone number");
        Pattern pattern = Pattern.compile(PHONE_VALIDATION);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid phone-number format");
        }
        return phoneNumber;
    }

    private static Address validateAddress(Address address) throws IllegalArgumentException {
        isNotNullOrEmpty(address.streetName(), "street name");
        isNotNullOrEmpty(address.postalCode(), "postal code");
        isNotNullOrEmpty(address.city(), "city");
        return address;
    }

    @Override
    public String toString() {
        return "Customer " + name + ", " + email;
    }


}
