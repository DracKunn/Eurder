package com.switchfully.eurder.users.customer;

import com.switchfully.eurder.orders.Order;
import com.switchfully.eurder.users.Address;
import com.switchfully.eurder.users.Name;
import com.switchfully.eurder.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import static com.switchfully.eurder.util.ValidatorsUtility.*;

public class Customer extends User {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final Address address;
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
