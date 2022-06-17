package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.ItemDTO;
import com.switchfully.eurder.items.ItemService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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


    public Order addItemsToNewOrder(String orderId, ItemDTO itemDTO, int amount) {
        Order order = new Order(orderId);
        order.addItemToOrder(itemService.getItemFromDTO(itemDTO), amount);
        orderRepository.placeOrder(order);
        logger.info("A new order with ID: " + orderId + " has been created. " + amount + " " + itemDTO.name() + " have been added.");
        return order;
    }

    public Order addItemsToExistingOrder(String orderId, ItemDTO itemDTO, int amount) {
        Order order = orderRepository.getOrderwithID(orderId);
        String newItemName = itemDTO.name();
        if (itemIsAlreadyInOrder(order, newItemName)) {
            int newTotal = addAmountToOrderWithSameItem(order, newItemName, amount);
            logger.info(amount + " " + itemDTO.name() + " have been added to your order with ID: " + orderId + ". The new total is: " + newTotal);
            return orderRepository.getOrderwithID(orderId);
        }
        order.addItemToOrder(itemService.getItemFromDTO(itemDTO), amount);
        logger.info(amount + " " + itemDTO.name() + " have been added to your order with ID: " + orderId + ".");
        return order;
    }

    public OrderDTO confirmOrder(OrderDTO orderDTO) {
        Order order = orderMapper.orderDTOToOrder(orderDTO);
        removeAmountFromStock(order);
        List<ItemGroup> orderedItems =  order.getOrderedItems().values().stream().toList();
        logger.info("Order " + order.getOrderId() + " has been confirmed.");
        for (ItemGroup orderedItem : orderedItems ){
            int amount = orderedItem.getAmount();
            String itemName = orderedItem.getSelectedItem().name();
            LocalDate shippingDate = orderedItem.getShippingDate();
            logger.info("The "+amount + " "+itemName+" will be shipped on "+shippingDate+".");
        }
        return orderDTO;
    }

    private boolean itemIsAlreadyInOrder(Order order, String newItemName) {
        return order.getOrderedItems().containsKey(newItemName);
    }

    private void removeAmountFromStock(Order order) {
        order.getOrderedItems().values().forEach(itemGroup -> itemService.removeItemAmountFromStock(itemGroup));
    }

    private int addAmountToOrderWithSameItem(Order order, String newItemName, int addedAmount) {
        ItemGroup orderedItem = order.getOrderedItemsByItemName(newItemName);
        orderedItem.updateAmount(addedAmount);
        return orderedItem.getAmount();
    }

}
