package com.switchfully.eurder.order.service;

import com.switchfully.eurder.item.api.dto.ItemDTO;
import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item.service.ItemMapper;
import com.switchfully.eurder.item.service.ItemService;
import com.switchfully.eurder.itemgroup.api.dto.ItemGroupDTO;
import com.switchfully.eurder.order.api.dto.CreateOrderDTO;
import com.switchfully.eurder.order.api.dto.OrderDTO;
import com.switchfully.eurder.itemgroup.domain.ItemGroup;
import com.switchfully.eurder.order.domain.Order;
import com.switchfully.eurder.order.domain.OrderRepository;
import com.switchfully.eurder.itemgroup.service.ItemGroupMapper;
import com.switchfully.eurder.user.api.dto.UserDTO;
import com.switchfully.eurder.user.domain.User;
import com.switchfully.eurder.user.service.UserMapper;
import com.switchfully.eurder.user.service.UserService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static com.switchfully.eurder.util.validation.ValidatorsUtility.validateCustomerHasThisOrder;

@Service
@AllArgsConstructor
public class OrderService {
    private final Logger orderServiceLogger = Logger.getLogger(this.getClass().getName());

    ItemMapper itemMapper;
    UserMapper userMapper;
    ItemGroupMapper itemGroupMapper;
    OrderMapper orderMapper;
    ItemService itemService;
    UserService userService;
    OrderRepository orderRepository;

    public OrderDTO addItemsToNewOrder(CreateOrderDTO createOrderDTO) {
        UserDTO userDTO = userService.getCustomerDTOById(createOrderDTO.customerId());
        ItemDTO itemToOrderDTO = itemService.getItemDTOById(createOrderDTO.itemId());
        Item itemToOrder = itemMapper.toEntity(itemToOrderDTO);
        ItemGroup itemGroup = new ItemGroup(itemToOrder, createOrderDTO.amount());
        ItemGroupDTO itemGroupDTO = itemGroupMapper.toDTO(itemGroup);
        Order order = orderMapper.toEntity(userDTO, new ArrayList<>(Collections.singleton(itemGroupDTO)));
        orderRepository.save(order);
        String itemsAddedMessage = "A new order with ID: " + order.getId() + " has been created for customer: " + userMapper.toEntity(userDTO).getName().toString() + ". " + createOrderDTO.amount() + " " + itemToOrderDTO.name() + " have been added.";
        orderServiceLogger.info(itemsAddedMessage);
        return orderMapper.toDTO(order);
    }

    public OrderDTO addItemsToExistingOrder(int orderId, CreateOrderDTO createOrderDTO) throws AccessDeniedException {
        UserDTO userDTO = userService.getCustomerDTOById(createOrderDTO.customerId());
        User customer = userMapper.toEntity(userDTO);
        ItemDTO itemToOrderDTO = itemService.getItemDTOById(createOrderDTO.itemId());
        Item itemToOrder = itemMapper.toEntity(itemToOrderDTO);
        Order order = findOrderById(orderId);
        validateCustomerHasThisOrder(customer, order);

        order.addItemToOrder(itemToOrder, createOrderDTO.amount());
        orderRepository.save(order);
        String itemsAddedMessage = createOrderDTO.amount() + " " + itemToOrderDTO.name() + " have been added to your order with ID: " + orderId + ".";
        orderServiceLogger.info(itemsAddedMessage);
        return orderMapper.toDTO(order);
    }

    public OrderDTO confirmOrder(int userId, int orderId) throws AccessDeniedException {
        OrderDTO orderDTO = getOrderDTOByOrderId(userId, orderId);
        Order order = orderMapper.toEntity(orderDTO);
        validateCustomerHasThisOrder(getCustomerById(userId), order);
        removeAmountFromStock(order);
        logOrderConfirmation(order);
        return orderDTO;
    }

    private void logOrderConfirmation(Order order) {
        List<ItemGroup> orderedItems = order.getOrderedItems();
        String OrderConfirmedMessage = "Order " + order.getId() + " has been confirmed.";
        orderServiceLogger.info(OrderConfirmedMessage);
        for (ItemGroup orderedItem : orderedItems) {
            int amount = orderedItem.getAmount();
            String itemName = orderedItem.getSelectedItem().getName();
            LocalDate shippingDate = orderedItem.getShippingDate();
            String ShippingItemsMessage = "The " + amount + " " + itemName + " will be shipped on " + shippingDate + ".";
            orderServiceLogger.info(ShippingItemsMessage);
        }
    }

    private void removeAmountFromStock(Order order) {
        order.getOrderedItems().forEach(itemGroup -> itemService.removeItemAmountFromStock(itemGroup));
    }

    public List<OrderDTO> getAllOrdersForUser(int customerId) {
        User customer = getCustomerById(customerId);
        List<Order> customerOrders = orderRepository.findAll().stream().filter(order -> order.getCustomer() == customer).toList();
        orderServiceLogger.info("all orders for customer: " + customerOrders);
        return orderMapper.listOfOrdertoOrderDTOList(customerOrders);
    }

    public OrderDTO getOrderDTOByOrderId(int customerId, int orderId) {
        Order order = validateOrder(customerId, orderId);
        return orderMapper.toDTO(order);
    }

    @NotNull
    private Order validateOrder(int customerId, int orderId) {
        Order order = findOrderById(orderId);
        User customer = getCustomerById(customerId);
        try {
            validateCustomerHasThisOrder(customer, order);
        } catch (AccessDeniedException exception) {
            orderServiceLogger.warning("this customer has no access to this order");
        }
        return order;
    }

    private User getCustomerById(int userId) {
        return userMapper.toEntity(getCustomerDTOById(userId));
    }

    private UserDTO getCustomerDTOById(int userId) {
        return userService.getCustomerDTOById(userId);
    }

    private Order findOrderById(int orderId) {
        Order foundOrder = orderRepository.findById(orderId).orElse(null);
        if (foundOrder == null) {
            throw new IllegalArgumentException("Order not found");
        }
        return foundOrder;
    }
}
