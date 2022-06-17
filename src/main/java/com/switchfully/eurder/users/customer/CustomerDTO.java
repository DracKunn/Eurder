package com.switchfully.eurder.users.customer;

import com.switchfully.eurder.users.Address;
import com.switchfully.eurder.users.Name;
import com.switchfully.eurder.users.UserDTO;

import java.util.Objects;

import static com.switchfully.eurder.util.ValidatorsUtility.validateAddress;
import static com.switchfully.eurder.util.ValidatorsUtility.validatePhoneNumber;

public class CustomerDTO extends UserDTO {

    private final Address address;
    private final String phoneNumber;

    public CustomerDTO(String useName,Name name, String email, Address address, String phoneNumber) {
        super(useName,name, email);
        this.address = validateAddress(address);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
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
        if (!(o instanceof CustomerDTO)) return false;
        if (!super.equals(o)) return false;
        CustomerDTO that = (CustomerDTO) o;
        return getAddress().equals(that.getAddress()) && getPhoneNumber().equals(that.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAddress(), getPhoneNumber());
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", name=" + name +
                ", email='" + email + '\'' +
                '}';
    }
}
