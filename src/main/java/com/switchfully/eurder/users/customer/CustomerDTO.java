package com.switchfully.eurder.users.customer;

import com.switchfully.eurder.users.Address;
import com.switchfully.eurder.users.Name;
import com.switchfully.eurder.users.UserDTO;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDTO that)) return false;
        return getAddress().equals(that.getAddress()) && getPhoneNumber().equals(that.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddress(), getPhoneNumber());
    }
}
