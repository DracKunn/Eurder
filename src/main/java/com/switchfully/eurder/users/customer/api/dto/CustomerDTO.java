package com.switchfully.eurder.users.customer.api.dto;

import com.switchfully.eurder.orders.domain.Order;
import com.switchfully.eurder.util.address.Address;
import com.switchfully.eurder.util.name.Name;

import java.util.List;

public record CustomerDTO(String userName, Name name, String email, Address address, String phoneNumber, List<Order> orders) {}


