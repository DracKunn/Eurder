package com.switchfully.eurder.orders;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    public Order orderDTOToOrder(OrderDTO orderDTO) {
        return new Order(orderDTO.customer(), orderDTO.orderID(), orderDTO.orderedItems());
    }

    public OrderDTO orderToOrderDTO(Order order) {
        return new OrderDTO(order.getCustomer(), order.getOrderId(), order.getOrderedItems());
    }

    public List<Order> listOfOrderDTOtoOrderList(List<OrderDTO> orderDTOList) {
        return orderDTOList.stream().map(this::orderDTOToOrder).toList();
    }

    public List<OrderDTO> listOfOrdertoOrderDTOList(List<Order> orderList) {
        return orderList.stream().map(this::orderToOrderDTO).toList();
    }
}
