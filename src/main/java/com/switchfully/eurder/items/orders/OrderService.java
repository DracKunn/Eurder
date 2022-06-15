package com.switchfully.eurder.items.orders;

import com.switchfully.eurder.items.ItemDTO;
import com.switchfully.eurder.items.ItemService;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class OrderService {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    OrderMapper orderMapper;
    OrderRepository orderRepository;

    ItemService itemService;


    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, ItemService itemService) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.itemService = itemService;
    }

    public void placeOrder(OrderDTO orderDTO) {
        Order order = orderMapper.orderDTOToOrder(orderDTO);
        orderRepository.placeOrder(order);

        // we want to remove the amount that is ordered from the stock of the item. for that we need to get the order, then the map of the ordered items containing a map of ordergroups which will lead to the item...

        removeAmountFromStock(order);

        logger.info("Order " + order.getOrderID() + " has been placed.");
    }

    public Order addItemsToNewOrder(String orderId, ItemDTO itemDTO, int amount) {
        Order order = new Order(orderId);
        order.addItemToOrder(itemService.ItemDTOToItem(itemDTO), amount);
        orderRepository.placeOrder(order);
        return order;
    }

    public Order addItemsToExistingOrder(String orderId, ItemDTO itemDTO, int amount) {
        Order order = orderRepository.getOrderwithID(orderId);
        order.addItemToOrder(itemService.ItemDTOToItem(itemDTO), amount);
        return order;
    }

    private void removeAmountFromStock(Order order) {
        order.getOrderedItems().values().forEach(itemGroup -> itemService.removeItemgroupAmountFromStock(itemGroup));

    }
}
