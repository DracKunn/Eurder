package com.switchfully.eurder.item.orders;

import com.switchfully.eurder.item.api.dto.CreateItemDTO;
import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item.domain.ItemRepository;
import com.switchfully.eurder.item.service.ItemMapper;
import com.switchfully.eurder.item.service.ItemService;
import com.switchfully.eurder.order.api.dto.order.OrderDTO;
import com.switchfully.eurder.order.domain.order.Order;
import com.switchfully.eurder.order.domain.order.OrderRepository;
import com.switchfully.eurder.order.service.itemgroup.ItemGroupMapper;
import com.switchfully.eurder.order.service.order.OrderMapper;
import com.switchfully.eurder.order.service.order.OrderService;
import com.switchfully.eurder.user.api.dto.customer.CreateCustomerDTO;
import com.switchfully.eurder.util.address.api.dto.AddressDTO;
import com.switchfully.eurder.util.address.domain.Address;
import com.switchfully.eurder.util.address.service.AddressMapper;
import com.switchfully.eurder.util.name.api.dto.NameDTO;
import com.switchfully.eurder.util.name.domain.Name;
import com.switchfully.eurder.user.domain.UserRepository;
import com.switchfully.eurder.user.domain.customer.Customer;
import com.switchfully.eurder.user.service.customer.CustomerMapper;
import com.switchfully.eurder.user.service.customer.CustomerService;
import com.switchfully.eurder.util.name.service.NameMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    Item headphones = new Item(
            "Headphones",
            "portable sound listening electronic device");
    Item apples = new Item(
            "Apples",
            "Fruit, tart, juicy");
    Item bananas = new Item(
            "Bananas",
            "Fruit, sweet, tasty");
    ItemMapper itemMapper = new ItemMapper();
    CreateItemDTO createHeadphonesDTO;
    CreateItemDTO createApplesDTO;
    CreateItemDTO createBananaDTO;
    ItemRepository itemRepository;
    ItemService itemService;

    //given customer + customer service
    String userName = "bruenor";
    Name name = new Name("Bruenor", "The Bard");
    NameMapper nameMapper = new NameMapper();
    NameDTO nameDTO = nameMapper.toDTO(name);
    String email = "bruenor@bardcollege.org";
    Address address = new Address(
            "streetName",
            666,
            "postalCode",
            "city"
    );
    AddressMapper addressMapper = new AddressMapper();
    AddressDTO addressDTO = addressMapper.toDTO(address);
    String phoneNumber = "+32444555666";
    CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO(
            userName,
            nameDTO,
            email,
            addressDTO,
            phoneNumber
    );
    UserRepository userRepository;
    CustomerMapper customerMapper = new CustomerMapper(nameMapper, addressMapper);
    CustomerService customerService;

    //order items in 2 orders
    String headphonesOrderId = "headphones";
    String fruitOrderId = "fruit";
    ItemGroupMapper itemGroupMapper = new ItemGroupMapper(itemMapper);
    OrderMapper orderMapper = new OrderMapper(customerMapper, itemGroupMapper);
    OrderRepository orderRepository;
    OrderService orderService;


    @BeforeEach
    void setUp() {
        //given items into warehouse
        createHeadphonesDTO = new CreateItemDTO(
                "Headphones",
                "portable sound listening electronic device",
                75.99,
                20);
        createApplesDTO = new CreateItemDTO(
                "Apples",
                "Fruit, tart, juicy",
                0.50,
                100);
        createBananaDTO = new CreateItemDTO(
                "Bananas",
                "Fruit, sweet, tasty",
                1,
                60);

        itemRepository = Mockito.mock(ItemRepository.class);
        itemService = new ItemService(itemMapper, itemRepository);

        itemService.addItem(createHeadphonesDTO);
        itemService.addItem(createApplesDTO);
        itemService.addItem(createBananaDTO);

        userRepository = Mockito.mock(UserRepository.class);
        customerService = new CustomerService(customerMapper, userRepository);

        customerService.registerNewCustomer(createCustomerDTO);

        orderRepository = Mockito.mock(OrderRepository.class);
        orderService = new OrderService(
                itemMapper,
                customerMapper,
                itemGroupMapper,
                orderMapper,
                itemService,
                customerService,
                orderRepository
        );


    }

    @Test
    @DisplayName("given a webshop and warehouse with items when i add items to order without an existing order then i can see my ordered items")
    void givenAWebshopAndWarehouseWithItemsWhenIAddItemsToOrderWithoutAnExistingOrderThenICanSeeMyOrderedItems() {


        //given
        int stock = 20;
        int amountToBuy = 5;
        headphones.setStock(stock);


        //when
        Order order = orderService.addItemsToNewOrder(userName, headphonesOrderId, headphonesDTO, amountToBuy);

        //then
        Order expected = orderMapper.toEntity(orderService.getOrderDTOByOrderId(userName, headphonesOrderId));
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
        int headphonesStock = headphones.getStock();
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
        int stockBefore = headphones.getStock();
        //when
        String orderId = "Buying-3-pairs-of-Headphones";
        Order order = orderService.addItemsToNewOrder(userName, orderId, headphonesDTO, 3);


        orderService.confirmOrder(userName, orderId);
        String itemName = headphones.getName();
        int actual = itemRepository.getItemByName(itemName).getStock();
        //then

        assertEquals(stockBefore - 3, actual);
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
