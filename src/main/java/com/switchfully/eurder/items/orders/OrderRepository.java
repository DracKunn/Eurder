package com.switchfully.eurder.items.orders;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepository {
    private final Map<String, Order> itemMap;

    public OrderRepository() {
        this.itemMap = new HashMap<>();
    }

    public void placeOrder(Order order) {

    }
}
