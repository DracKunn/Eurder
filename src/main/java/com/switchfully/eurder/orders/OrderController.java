package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.ItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final java.util.logging.Logger logger = Logger.getLogger(this.getClass().getName());

    OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping(path ="/{itemName}/add-item-to-new-order", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO addItemToNewOrder(@RequestBody OrderRequest orderRequest,@PathVariable String itemName){
        String orderId = orderRequest.orderId();
        int amount = orderRequest.amount();
        logger.info("Looking to order the following item: "+itemName);
        ItemDTO itemDTO = orderService.itemService.getItemDTOFromItemName(itemName);
        Order order = orderService.addItemsToNewOrder(orderId,itemDTO,amount);
        logger.info(amount + " " + itemName + " added to new order "+ orderId+".");
        return orderService.orderMapper.orderToOrderDTO(order);
    }

    @PostMapping(path ="/{itemName}/add-item-to-order", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO addItemToExistingOrder(@RequestBody OrderRequest orderRequest,@PathVariable String itemName){
        String orderId = orderRequest.orderId();
        int amount = orderRequest.amount();
        ItemDTO itemDTO = orderService.itemService.getItemDTOFromItemName(itemName);
        Order order = orderService.addItemsToExistingOrder(orderId,itemDTO,amount);
        logger.info(amount + " " + itemName + " added to order "+ orderId+".");
        return orderService.orderMapper.orderToOrderDTO(order);
    }

    @PostMapping(path="/{orderId}/confirm",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OrderDTO confirmOrder(@PathVariable String orderId){
        Order order = orderService.orderRepository.getOrderwithID(orderId);
        OrderDTO orderDTO = orderService.orderMapper.orderToOrderDTO(order);
        logger.info("Order "+ orderId+" confirmed.");
        return orderService.confirmOrder(orderDTO);
    }


}