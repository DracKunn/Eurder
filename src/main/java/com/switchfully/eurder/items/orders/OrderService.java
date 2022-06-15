package com.switchfully.eurder.items.orders;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    OrderMapper orderMapper;
    OrderRepository orderRepository;

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

//    public void placeOrder(OrderDTO orderDTO) {
//        Order order = orderMapper.orderDTOToOrder(orderDTO);
//        orderRepository.placeOrder(order);
//    }
}
