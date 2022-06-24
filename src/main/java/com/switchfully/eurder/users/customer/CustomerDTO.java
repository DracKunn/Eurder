package com.switchfully.eurder.users.customer;

import com.switchfully.eurder.orders.Order;
import com.switchfully.eurder.orders.OrderDTO;
import com.switchfully.eurder.users.Address;
import com.switchfully.eurder.users.Name;

import java.util.List;

public record CustomerDTO(String userName, Name name, String email, Address address, String phoneNumber, List<Order> orders) {}


