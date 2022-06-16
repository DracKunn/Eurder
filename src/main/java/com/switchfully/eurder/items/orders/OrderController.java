package com.switchfully.eurder.items.orders;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    public OrderDTO confirmOrder(OrderDTO orderDTO){
        orderService.confirmOrder(orderDTO);
        return orderDTO;
    }
}
