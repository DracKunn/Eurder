package com.switchfully.eurder.orders;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepository {
    private final Map<String, Order> orderMap;

    public OrderRepository() {
        this.orderMap = new HashMap<>();
    }

    public void placeOrder(Order order) {
        orderMap.put(order.getOrderID(), order);
    }

    public Order getOrderwithID(String id) {
        return orderMap.get(id);
    }

    public Map<String, Order> getOrderMap() {
        return orderMap;
    }
}
