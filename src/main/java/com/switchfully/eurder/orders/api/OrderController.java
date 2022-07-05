package com.switchfully.eurder.orders.api;

import com.switchfully.eurder.items.api.dto.ItemDTO;
import com.switchfully.eurder.orders.api.dto.OrderDTO;
import com.switchfully.eurder.orders.domain.Order;
import com.switchfully.eurder.orders.domain.OrderRequest;
import com.switchfully.eurder.orders.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/{userName}/orders")
public class OrderController {
    private final java.util.logging.Logger logger = Logger.getLogger(this.getClass().getName());

    OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping(path ="/{itemName}/add-item-to-new-order", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO addItemToNewOrder(@RequestBody OrderRequest orderRequest, @PathVariable String itemName, @PathVariable String userName){
        String orderId = orderRequest.orderId();
        int amount = orderRequest.amount();
        logger.info("Looking to create a new order '"+orderId+"' with the following item: "+itemName);
        ItemDTO itemDTO = orderService.itemService.getItemDTOFromItemName(itemName);
        Order order = orderService.addItemsToNewOrder(userName,orderId,itemDTO,amount);
        logger.info(amount + " " + itemName + " added to new order "+ orderId+".");
        return orderService.orderMapper.orderToOrderDTO(order);
    }

    @PostMapping(path ="/{itemName}/add-item-to-order", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO addItemToExistingOrder(@RequestBody OrderRequest orderRequest,@PathVariable String itemName,@PathVariable String userName) throws AccessDeniedException {
        String orderId = orderRequest.orderId();
        int amount = orderRequest.amount();
        logger.info("Looking to add item "+itemName+" to order '"+orderId+"'.");
        ItemDTO itemDTO = orderService.itemService.getItemDTOFromItemName(itemName);
        Order order = orderService.addItemsToExistingOrder(userName,orderId,itemDTO,amount);
        logger.info(amount + " " + itemName + " added to order "+ orderId+".");
        return orderService.orderMapper.orderToOrderDTO(order);
    }

    @PostMapping(path="/{orderId}/confirm", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OrderDTO confirmOrder(@PathVariable String orderId,@PathVariable String userName) throws AccessDeniedException {
        OrderDTO orderDTO = orderService.confirmOrder(userName,orderId);
        logger.info("Order "+ orderId+" confirmed.");
        return orderDTO;
    }
    @GetMapping(produces = "application/json")
    public List<OrderDTO> getAllOrders(@PathVariable String userName){
        return orderService.getAllOrdersForUser(userName);
    }
}
