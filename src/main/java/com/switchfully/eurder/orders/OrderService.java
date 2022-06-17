package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.ItemDTO;
import com.switchfully.eurder.items.ItemService;
import com.switchfully.eurder.users.customer.Customer;
import com.switchfully.eurder.users.customer.CustomerDTO;
import com.switchfully.eurder.users.customer.CustomerService;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import static com.switchfully.eurder.util.ValidatorsUtility.validateCustomerHasThisOrder;

@Service
public class OrderService {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    OrderMapper orderMapper;
    OrderRepository orderRepository;
    ItemService itemService;
    CustomerService customerService;


    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, ItemService itemService, CustomerService customerService) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.itemService = itemService;
        this.customerService = customerService;
    }


    public Order addItemsToNewOrder(String userName, String orderId, ItemDTO itemDTO, int amount) {
        Order order = new Order(getCustomerFromUserName(userName), orderId);
        order.addItemToOrder(itemService.getItemFromDTO(itemDTO), amount);
        orderRepository.placeOrder(order);
        logger.info("A new order with ID: " + orderId + " has been created. " + amount + " " + itemDTO.name() + " have been added.");
        return order;
    }

    public Order addItemsToExistingOrder(String userName, String orderId, ItemDTO itemDTO, int amount) throws AccessDeniedException {
        Order order = orderRepository.getOrderwithID(orderId);
        validateCustomerHasThisOrder(getCustomerFromUserName(userName), order);
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

    public OrderDTO confirmOrder(String userName, String orderId) throws AccessDeniedException {
        OrderDTO orderDTO = getOrderDTOByOrderId(userName, orderId);
        Order order = orderMapper.orderDTOToOrder(orderDTO);
        validateCustomerHasThisOrder(getCustomerFromUserName(userName), order);
        removeAmountFromStock(order);
        logOrderConfirmation(order);
        return orderDTO;
    }

    private void logOrderConfirmation(Order order) {
        List<ItemGroup> orderedItems = order.getOrderedItems().values().stream().toList();
        logger.info("Order " + order.getOrderId() + " has been confirmed.");
        for (ItemGroup orderedItem : orderedItems) {
            int amount = orderedItem.getAmount();
            String itemName = orderedItem.getSelectedItem().name();
            LocalDate shippingDate = orderedItem.getShippingDate();
            logger.info("The " + amount + " " + itemName + " will be shipped on " + shippingDate + ".");
        }
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

    public List<OrderDTO> getAllOrdersForUser(String userName) {
        return List.of();
    }

    public OrderDTO getOrderDTOByOrderId(String userName, String orderId) throws AccessDeniedException {
        Order order = orderRepository.getOrderwithID(orderId);
        Customer customer = getCustomerFromUserName(userName);
        validateCustomerHasThisOrder(customer, order);
        return orderMapper.orderToOrderDTO(order);
    }

    private Customer getCustomerFromUserName(String userName) {
        CustomerDTO customerDTO = customerService.getCustomerByUserName(userName);
        return customerService.customerMapper.customerDTOToCustomer(customerDTO);
    }


}
