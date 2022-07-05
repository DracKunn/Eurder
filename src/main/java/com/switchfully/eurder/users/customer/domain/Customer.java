package com.switchfully.eurder.users.customer.domain;

import com.switchfully.eurder.orders.domain.Order;
import com.switchfully.eurder.util.address.Address;
import com.switchfully.eurder.util.name.Name;
import com.switchfully.eurder.users.user.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import static com.switchfully.eurder.util.validation.ValidatorsUtility.*;
@Entity
@Table
public class Customer extends User {
    @Transient
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    @JoinColumn
    private final Address address;
    @Column
    private final String phoneNumber;

    private final List<Order> orders;

    public Customer(String useName,Name name, String email, Address address, String phoneNumber) {
        super(useName,name, email);
        this.address = validateAddress(address);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
        this.orders = new ArrayList<>();
    }
    public Customer(String useName,Name name, String email, Address address, String phoneNumber, List<Order> orders) {
        super(useName,name, email);
        this.address = validateAddress(address);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
        this.orders = orders;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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
        return getAddress().equals(customer.getAddress()) && getPhoneNumber().equals(customer.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAddress(), getPhoneNumber());
    }

    public List<Order> getAllOrders() {
        return this.orders;
    }

    public void addOrder(Order order){
        this.orders.add(order);
        logger.info("order: "+order+" has been added to Customer: "+this+". \n The Order List is now: "+ this.orders);
    }
}
