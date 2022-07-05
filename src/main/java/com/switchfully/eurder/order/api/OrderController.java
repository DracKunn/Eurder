package com.switchfully.eurder.order.api;

import com.switchfully.eurder.item.api.dto.ItemDTO;
import com.switchfully.eurder.order.api.dto.order.CreateOrderDTO;
import com.switchfully.eurder.order.api.dto.order.OrderDTO;
import com.switchfully.eurder.order.domain.order.Order;
import com.switchfully.eurder.order.service.order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("{userId}/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping(path ="/add-item-to-new-order", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO addItemToNewOrder(@RequestBody CreateOrderDTO createOrderDTO){
        return orderService.addItemsToNewOrder(createOrderDTO);
    }

    @PostMapping(path ="/{orderId}/add-item-to-order", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO addItemToExistingOrder(@RequestBody CreateOrderDTO createOrderDTO, @PathVariable int orderId) throws AccessDeniedException {
        return orderService.addItemsToExistingOrder(orderId,createOrderDTO);
    }

    @PostMapping(path="/{orderId}/confirm", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OrderDTO confirmOrder(@PathVariable int userId,@PathVariable int orderId) throws AccessDeniedException {
        return orderService.confirmOrder(userId,orderId);
    }
    @GetMapping(produces = "application/json")
    public List<OrderDTO> getAllOrders(@PathVariable int userId){
        return orderService.getAllOrdersForUser(userId);
    }
}
