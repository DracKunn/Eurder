package com.switchfully.eurder.orders.api.dto;

import com.switchfully.eurder.orders.domain.ItemGroup;
import com.switchfully.eurder.users.customer.domain.Customer;

import java.util.Map;

public record OrderDTO(Customer customer, String orderID, Map<String, ItemGroup> orderedItems) {

}
