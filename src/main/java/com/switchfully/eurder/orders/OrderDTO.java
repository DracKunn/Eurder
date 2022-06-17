package com.switchfully.eurder.orders;

import com.switchfully.eurder.users.customer.Customer;

import java.util.Map;

public record OrderDTO(Customer customer, String orderID, Map<String, ItemGroup> orderedItems) {

}
