package com.switchfully.eurder.user.domain;

import com.switchfully.eurder.util.address.domain.Address;
import com.switchfully.eurder.util.name.domain.Name;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

import static com.switchfully.eurder.util.validation.ValidatorsUtility.*;
@Entity
@DiscriminatorValue("2")
@NoArgsConstructor
@Getter
public class Customer extends User {
    @JoinColumn
    @OneToOne
    private Address address;
    @Column
    private String phoneNumber;


    public Customer(String useName,Name name, String email, Address address, String phoneNumber) {
        super(useName,name, email);
        this.address = validateAddress(address);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
    }

    public Customer(int id,String useName,Name name, String email, Address address, String phoneNumber) {
        super(id,useName,name, email);
        this.address = validateAddress(address);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
    }

    @Override
    public String toString() {
        return "Customer " + name + ", " + email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getAddress(), customer.getAddress()) && Objects.equals(getPhoneNumber(), customer.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAddress(), getPhoneNumber());
    }
}
