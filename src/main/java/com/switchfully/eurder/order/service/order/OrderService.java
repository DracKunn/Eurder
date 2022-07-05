package com.switchfully.eurder.order.service.order;

import com.switchfully.eurder.item.api.dto.ItemDTO;
import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item.service.ItemMapper;
import com.switchfully.eurder.item.service.ItemService;
import com.switchfully.eurder.order.api.dto.order.CreateOrderDTO;
import com.switchfully.eurder.order.api.dto.order.OrderDTO;
import com.switchfully.eurder.order.domain.itemgroup.ItemGroup;
import com.switchfully.eurder.order.domain.order.Order;
import com.switchfully.eurder.order.domain.order.OrderRepository;
import com.switchfully.eurder.order.service.itemgroup.ItemGroupMapper;
import com.switchfully.eurder.user.api.dto.customer.CustomerDTO;
import com.switchfully.eurder.user.domain.customer.Customer;
import com.switchfully.eurder.user.service.customer.CustomerMapper;
import com.switchfully.eurder.user.service.customer.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.switchfully.eurder.util.validation.ValidatorsUtility.validateCustomerHasThisOrder;

@Service
@AllArgsConstructor
public class OrderService {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    ItemMapper itemMapper;
    CustomerMapper customerMapper;
    ItemGroupMapper itemGroupMapper;
    OrderMapper orderMapper;
    ItemService itemService;
    CustomerService customerService;
    OrderRepository orderRepository;


    public OrderDTO addItemsToNewOrder(CreateOrderDTO createOrderDTO) {
        CustomerDTO customerDTO = customerService.getCustomerDTOById(createOrderDTO.customerId());
        ItemDTO itemToOrderDTO = itemService.getItemDTOById(createOrderDTO.itemId());
        Item itemToOrder = itemMapper.toEntity(itemToOrderDTO);
        ItemGroup itemGroup = new ItemGroup(itemToOrder, createOrderDTO.amount());
        Order order = orderMapper.toEntity(customerDTO, List.of(itemGroupMapper.toDTO(itemGroup)));
        orderRepository.save(order);
        String itemsAddedMessage = "A new order with ID: " + order.getId() + " has been created for customer: " + customerMapper.toEntity(customerDTO).getFullName() + ". " + createOrderDTO.amount() + " " + itemToOrderDTO.name() + " have been added.";
        logger.info(itemsAddedMessage);
        return orderMapper.toDTO(order);
    }

    public OrderDTO addItemsToExistingOrder(int orderId, CreateOrderDTO createOrderDTO) throws AccessDeniedException {
        CustomerDTO customerDTO = customerService.getCustomerDTOById(createOrderDTO.customerId());
        Customer customer = customerMapper.toEntity(customerDTO);
        ItemDTO itemToOrderDTO = itemService.getItemDTOById(createOrderDTO.itemId());
        Item itemToOrder = itemMapper.toEntity(itemToOrderDTO);
        Order order = findOrderById(orderId);
        validateCustomerHasThisOrder(customer, order);
//        if (itemIsAlreadyInOrder(order, item)) {
//            int newTotal = addAmountToOrderWithSameItem(order, newItemName, amount);
////            logger.info(amount + " " + itemDTO.name() + " have been added to your order with ID: " + orderId + ". The new total is: " + newTotal);
//            return orderRepository.getOrderwithID(orderId);
//        }
        order.addItemToOrder(itemToOrder, createOrderDTO.amount());
        String itemsAddedMessage = createOrderDTO.amount() + " " + itemToOrderDTO.name() + " have been added to your order with ID: " + orderId + ".";
        logger.info(itemsAddedMessage);
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
        logger.info(OrderConfirmedMessage);
        for (ItemGroup orderedItem : orderedItems) {
            int amount = orderedItem.getAmount();
            String itemName = orderedItem.getSelectedItem().getName();
            LocalDate shippingDate = orderedItem.getShippingDate();
            String ShippingItemsMessage = "The " + amount + " " + itemName + " will be shipped on " + shippingDate + ".";
            logger.info(ShippingItemsMessage);
        }
    }

    private boolean itemIsAlreadyInOrder(Order order, Item itemToAdd) {
        return order.getOrderedItems().contains(itemToAdd);
    }


    private void removeAmountFromStock(Order order) {
        order.getOrderedItems().forEach(itemGroup -> itemService.removeItemAmountFromStock(itemGroup));
    }

//    private int addAmountToOrderWithSameItem(Order order, Item item, int addedAmount) {
//        ItemGroup orderedItem = order.getOrderedItems().stream().filter(itemGroup -> itemGroup.getSelectedItem()==item).;
//        orderedItem.updateAmount(addedAmount);
//        return orderedItem.getAmount();
//    }

    public List<OrderDTO> getAllOrdersForUser(int customerId) {
        Customer customer = getCustomerById(customerId);
        List<Order> customerOrders = orderRepository.findAll().stream().filter(order -> order.getCustomer() == customer).collect(Collectors.toList());
        logger.info("all orders for customer: " + customerOrders);
        return orderMapper.listOfOrdertoOrderDTOList(customerOrders);
    }


    public OrderDTO getOrderDTOByOrderId(int customerId, int orderId) {
        Order order = findOrderById(orderId);
        Customer customer = getCustomerById(customerId);
        try {
            validateCustomerHasThisOrder(customer, order);
        } catch (AccessDeniedException exception) {
            logger.warning("this customer has no access to this order");
        }
        return orderMapper.toDTO(order);
    }

    private Customer getCustomerById(int userId) {
        return getCustomerById(userId);
    }

    private Order findOrderById(int orderId) {
        Order foundOrder = orderRepository.findById(orderId).orElse(null);
        if (foundOrder == null) {
            throw new IllegalArgumentException("Order not found");
        }
        return foundOrder;
    }


}
