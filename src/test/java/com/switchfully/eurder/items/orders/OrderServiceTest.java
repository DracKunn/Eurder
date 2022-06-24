package com.switchfully.eurder.items.orders;

import com.switchfully.eurder.items.*;
import com.switchfully.eurder.orders.*;
import com.switchfully.eurder.users.Address;
import com.switchfully.eurder.users.Name;
import com.switchfully.eurder.users.UserRepository;
import com.switchfully.eurder.users.customer.Customer;
import com.switchfully.eurder.users.customer.CustomerMapper;
import com.switchfully.eurder.users.customer.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    Item headphones = new Item("Headphones", "portable sound listening electronic device");
    Item apples = new Item("Apples", "Fruit, tart, juicy");
    Item bananas = new Item("Bananas", "Fruit, sweet, tasty");
    ItemMapper itemMapper = new ItemMapper();
    ItemDTO headphonesDTO = itemMapper.itemToItemDTO(headphones);
    ItemDTO applesDTO = itemMapper.itemToItemDTO(apples);
    ItemDTO bananaDTO = itemMapper.itemToItemDTO(bananas);
    ItemRepository itemRepository;
    ItemService itemService;

    //given customer + customer service
    String userName = "bruenor";
    Name name = new Name("Bruenor", "The Bard");
    String email = "bruenor@bardcollege.org";
    Address address = new Address("streetName", 666, "postalCode", "city");
    String phoneNumber = "+32444555666";
    Customer customer = new Customer(userName, name, email, address, phoneNumber);
    UserRepository userRepository;
    CustomerMapper customerMapper = new CustomerMapper();
    CustomerService customerService;

    //order items in 2 orders
    String headphonesOrderId = "headphones";
    String fruitOrderId = "fruit";
    OrderMapper orderMapper = new OrderMapper();
    OrderRepository orderRepository;
    OrderService orderService;


    @BeforeEach
    void setUp() {
        //given items into warehouse
        headphones.setPrice(75.99).setStock(20);
        apples.setPrice(0.50).setStock(100);
        bananas.setPrice(1).setStock(60);

        itemRepository = new ItemRepository();
        itemService = new ItemService(itemMapper, itemRepository);

        itemService.addItem(headphonesDTO);
        itemService.addItem(applesDTO);
        itemService.addItem(bananaDTO);

        userRepository = new UserRepository();
        customerService = new CustomerService(customerMapper, userRepository);

        userRepository.addNewCustomer(customer);

        orderRepository = new OrderRepository();
        orderService = new OrderService(orderMapper, orderRepository, itemService, customerService);


    }

    @Test
    @DisplayName("given a webshop and warehouse with items when i add items to order without an existing order then i can see my ordered items")
    void givenAWebshopAndWarehouseWithItemsWhenIAddItemsToOrderWithoutAnExistingOrderThenICanSeeMyOrderedItems() throws AccessDeniedException {


        //given
        int stock = 20;
        int amountToBuy = 5;
        headphones.setStock(stock);


        //when
        Order order = orderService.addItemsToNewOrder(userName, headphonesOrderId, headphonesDTO, amountToBuy);

        //then
        Order expected = orderMapper.orderDTOToOrder(orderService.getOrderDTOByOrderId(userName, headphonesOrderId));
        assertEquals(expected, order);
    }

    @Test
    @DisplayName("given a webshop and warehouse with items and an existing order, when i add another item to my order then i can see both items in my order")
    void givenAWebshopAndWarehouseWithItemsAndAnExistingOrderWhenIAddAnotherItemToMyOrderThenICanSeeBothItemsInMyOrder() throws AccessDeniedException {

        //given

        orderService.addItemsToNewOrder(userName, fruitOrderId, applesDTO, 50);

        //when
        Order secondOrderDTO = orderService.addItemsToExistingOrder(userName, fruitOrderId, bananaDTO, 20);

        //then
        assertTrue(orderRepository.getOrderMap().containsValue(secondOrderDTO));
    }

    @Test
    @DisplayName("given a webshop, when i place the order, the amount is removed from the stock")
    void givenAWebshopWhenIPlaceTheOrderTheAmountIsRemovedFromTheStock() throws AccessDeniedException {

        //given
        int headphonesStock = 50;
        int amountOfHeadphonesToBuy = 5;
        headphones.setStock(headphonesStock);

        String orderId = "buying-5-headphones";
        orderService.addItemsToNewOrder(userName, orderId, headphonesDTO, amountOfHeadphonesToBuy);

        //when

        orderService.confirmOrder(userName, orderId);
        //then
        String itemName = headphones.getName();
        int actual = itemRepository.getItemByName(itemName).getStock();
        int expected = headphonesStock - amountOfHeadphonesToBuy;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("given an item, when i create a new order then the order exists and contains the item.")
    void givenAnItemWhenICreateANewOrderThenTheOrderExistsAndContainsTheItem() {

        //when
        Order actual = orderService.addItemsToNewOrder(userName, "Buying-one-pair-of-Headphones", headphonesDTO, 1);

        //then
        assertTrue(orderRepository.getOrderMap().containsValue(actual));
    }


    @Test
    @DisplayName("given an item with a stock and a webshop, when we confirm an order more than the current stock the stock is zero")
    void givenAnItemWithAStockAndAWebshopWhenWeOrderMoreThanTheCurrentStockTheStockIsZero() throws AccessDeniedException {

        //given
        headphones.setStock(2);

        //when
        String orderId = "Buying-3-pairs-of-Headphones";
        Order order = orderService.addItemsToNewOrder(userName, orderId, headphonesDTO, 3);


        orderService.confirmOrder(userName, orderId);
        String itemName = headphones.getName();
        int actual = itemRepository.getItemByName(itemName).getStock();
        //then

        assertEquals(0, actual);
    }

    @Test
    @DisplayName("given an order ID containing a space, when adding an item to new order, then throws exception")
    void givenAnOrderIdContainingASpaceWhenAddingAnItemToNewOrderThenThrowsException() {

        //given
        String orderId = "twee woorden";

        //when / then
        assertThrows(IllegalArgumentException.class, () -> orderService.addItemsToNewOrder(userName, orderId, headphonesDTO, 5));

    }

    @Test
    @DisplayName("given an item and an order of that item, when I order more of the same item, the amount of the item order increases appropriately")
    void givenAnItemAndAnOrderOfThatItemWhenIOrderMoreOfTheSameItemTheAmountOfTheItemOrderIncreasesAppropriately() throws AccessDeniedException {
        //given

        String orderId = "order";
        Order order = orderService.addItemsToNewOrder(userName, orderId, applesDTO, 5);
        //when
        orderService.addItemsToExistingOrder(userName, orderId, applesDTO, 5);
        //then
        assertEquals(10, order.getOrderedItems().get(applesDTO.name()).getAmount());
    }

    @Test
    @DisplayName("given an item and an order, when I order more of the same item and the stock is insufficient, then updated with a later shipping date")
    void givenAnItemAndAnOrderWhenIOrderMoreOfTheSameItemAndTheStockIsInsufficientThenWithALaterShippingDate() throws AccessDeniedException {

        //given

        apples.setPrice(0.1).setStock(100);

        String orderId = "order";
        Order order = orderService.addItemsToNewOrder(userName, orderId, applesDTO, 5);
        //when
        orderService.addItemsToExistingOrder(userName, orderId, applesDTO, 100);

        //then
        LocalDate actual = order.getOrderedItems().get(applesDTO.name()).getShippingDate();
        LocalDate expected = LocalDate.now().plusWeeks(1);
        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("given an item and an order that is more than the stock, when i order more of the same item, the amount ordered increases appropriately")
    void givenAnItemAndAnOrderThatIsMoreThanTheStockWhenIOrderMoreOfTheSameItemTheAmountOrderedIncreasesAppropriately() throws AccessDeniedException {

        //given
        bananas.setPrice(0.1).setStock(100);

        String orderId = "order";
        Order order = orderService.addItemsToNewOrder(userName, orderId, bananaDTO, 5);
        //when
        orderService.addItemsToExistingOrder(userName, orderId, bananaDTO, 100);

        //then
        int actual = order.getOrderedItems().get(bananaDTO.name()).getAmount();
        assertEquals(105, actual);

    }

    @Test
    @DisplayName("given three items, spread over two orders, for one customer, then when we get all orders, a list is returned with all orders for that user")
    void givenThreeItemsSpreadOverTwoOrdersForOneCustomerThenWhenWeGetAllOrdersAListIsReturnedWithAllOrdersForThatUser() throws AccessDeniedException {

        //given
        orderService.addItemsToNewOrder(userName, headphonesOrderId, headphonesDTO, 5);
        orderService.addItemsToNewOrder(userName, fruitOrderId, applesDTO, 20);
        orderService.addItemsToExistingOrder(userName, fruitOrderId, bananaDTO, 20);
        //when
        List<OrderDTO> actual = orderService.getAllOrdersForUser(userName);
        //then
        OrderDTO headphonesOrderDTO = orderService.getOrderDTOByOrderId(userName, headphonesOrderId);
        OrderDTO fruitOrderDTO = orderService.getOrderDTOByOrderId(userName, fruitOrderId);
        List<OrderDTO> expected = List.of(headphonesOrderDTO, fruitOrderDTO);
        assertEquals(expected, actual);

    }

}
