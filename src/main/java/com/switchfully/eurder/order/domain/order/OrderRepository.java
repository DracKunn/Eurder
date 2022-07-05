package com.switchfully.eurder.order.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
//    private final Map<String, Order> orderMap;
//
//    public OrderRepository() {
//        this.orderMap = new HashMap<>();
//    }
//
//    public void placeOrder(Order order) {
//        orderMap.put(order.getOrderId(), order);
//    }
//
//    public Order getOrderwithID(String id) {
//        return orderMap.get(id);
//    }
//
//    public Map<String, Order> getOrderMap() {
//        return orderMap;
//    }
}
