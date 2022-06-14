package com.switchfully.eurder.users.customer;

import com.switchfully.eurder.users.Address;
import com.switchfully.eurder.users.Name;
import com.switchfully.eurder.users.UserDTO;

public class CustomerDTO extends UserDTO {

    private final Address address;
    private final String phoneNumber;

    public CustomerDTO(Name name, String email, Address address, String phoneNumber) {
        super(name, email);
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
