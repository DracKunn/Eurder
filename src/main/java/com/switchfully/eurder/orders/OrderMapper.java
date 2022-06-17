package com.switchfully.eurder.orders;

import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order orderDTOToOrder(OrderDTO orderDTO) {
        return new Order(orderDTO.customer(),orderDTO.orderID(),orderDTO.orderedItems());
    }

    public OrderDTO orderToOrderDTO(Order order){
        return new OrderDTO(order.getCustomer(),order.getOrderId(),order.getOrderedItems());
    }
}
