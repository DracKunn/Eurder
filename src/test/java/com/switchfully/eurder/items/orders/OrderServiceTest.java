package com.switchfully.eurder.items.orders;

import com.switchfully.eurder.items.*;
import com.switchfully.eurder.orders.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    @Test
    @DisplayName("given a webshop and warehouse with items when i add items to order without an existing order then i can see my ordered items")
    void givenAWebshopAndWarehouseWithItemsWhenIAddItemsToOrderWithoutAnExistingOrderThenICanSeeMyOrderedItems() {


        //given
        int headphonesStock = 50;
        int amountOfHeadphonesToBuy = 5;
        Item headphones = new Item("Headphones", "portable sound listening electronic device");
        headphones.setPrice(50.50).setStock(headphonesStock);

        ItemRepository warehouse = new ItemRepository();
        warehouse.addItem(headphones);

        ItemMapper itemMapper = new ItemMapper();
        ItemDTO headphonesDTO = itemMapper.itemToItemDTO(headphones);

        ItemService webStock = new ItemService(itemMapper, warehouse);
        OrderRepository shoppingBasket = new OrderRepository();
        OrderMapper orderMapper = new OrderMapper();
        OrderService orderService = new OrderService(orderMapper, shoppingBasket, webStock);

        //when
        Order order = orderService.addItemsToNewOrder("ID", headphonesDTO, amountOfHeadphonesToBuy);

        //then
        Order expected = shoppingBasket.getOrderwithID("ID");
        assertEquals(expected, order);
    }

    @Test
    @DisplayName("given a webshop and warehouse with items and an existing order, when i add another item to my order then i can see both items in my order")
    void givenAWebshopAndWarehouseWithItemsAndAnExistingOrderWhenIAddAnotherItemToMyOrderThenICanSeeBothItemsInMyOrder() {

        //given
        int headphonesStock = 50;
        int amountOfHeadphonesToBuy = 5;
        Item headphones = new Item("Headphones", "portable sound listening electronic device");
        headphones.setPrice(50.50).setStock(headphonesStock);

        Item sweaters = new Item("Sweater", "Warm clothing covering the torso and arms");
        sweaters.setPrice(25.75).setStock(100);

        ItemRepository warehouse = new ItemRepository();
        warehouse.addItem(headphones);
        warehouse.addItem(sweaters);

        ItemMapper itemMapper = new ItemMapper();
        ItemDTO headphonesDTO = itemMapper.itemToItemDTO(headphones);
        ItemDTO sweatersDTO = itemMapper.itemToItemDTO(sweaters);

        ItemService webStock = new ItemService(itemMapper, warehouse);
        OrderRepository shoppingBasket = new OrderRepository();
        OrderMapper orderMapper = new OrderMapper();
        OrderService orderService = new OrderService(orderMapper, shoppingBasket, webStock);

        Order orderDTO = orderService.addItemsToNewOrder("Shopping", headphonesDTO, amountOfHeadphonesToBuy);

        //when
        Order secondOrderDTO = orderService.addItemsToExistingOrder("Shopping", sweatersDTO, 20);

        //then
        assertTrue(shoppingBasket.getOrderMap().containsValue(secondOrderDTO));
    }

    @Test
    @DisplayName("given a webshop, when i place the order, the amount is removed from the stock")
    void givenAWebshopWhenIPlaceTheOrderTheAmountIsRemovedFromTheStock() {

        //given
        int headphonesStock = 50;
        int amountOfHeadphonesToBuy = 5;
        Item headphones = new Item("Headphones", "portable sound listening electronic device");
        headphones.setPrice(50.50).setStock(headphonesStock);

        ItemRepository warehouse = new ItemRepository();
        ItemMapper itemMapper = new ItemMapper();
        ItemDTO headphonesDTO = itemMapper.itemToItemDTO(headphones);
        ItemService webStock = new ItemService(itemMapper, warehouse);

        webStock.addItem(headphonesDTO);

        OrderRepository orderRepository = new OrderRepository();
        OrderMapper orderMapper = new OrderMapper();
        OrderService webShop = new OrderService(orderMapper, orderRepository, webStock);

        Order order = webShop.addItemsToNewOrder("buying 5 headphones", headphonesDTO, amountOfHeadphonesToBuy);
        OrderDTO orderDTO = orderMapper.orderToOrderDTO(order);


        //when

        webShop.confirmOrder(orderDTO);
        //then
        String itemName = headphones.getName();
        int actual = warehouse.getItemByName(itemName).getStock();
        int expected = headphonesStock - amountOfHeadphonesToBuy;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("given an item, when i create a new order then the order exists and contains the item.")
    void givenAnItemWhenICreateANewOrderThenTheOrderExistsAndContainsTheItem() {

        //given
        Item headphones = new Item("Headphones", "portable sound listening electronic device");
        headphones.setPrice(50.50).setStock(100);

        OrderRepository orderRepository = new OrderRepository();

        ItemMapper itemMapper = new ItemMapper();

        OrderService orderService = new OrderService(new OrderMapper(), orderRepository, new ItemService(itemMapper, new ItemRepository()));
        //when
        Order actual = orderService.addItemsToNewOrder("Buying one pair of Headphones", itemMapper.itemToItemDTO(headphones), 1);

        //then
        assertTrue(orderRepository.getOrderMap().containsValue(actual));
    }


    @Test
    @DisplayName("given an item with a stock and a webshop, when we confirm an order more than the current stock the stock is zero")
    void givenAnItemWithAStockAndAWebshopWhenWeOrderMoreThanTheCurrentStockTheStockIsZero() {

        //given
        Item headphones = new Item("Headphones", "portable sound listening electronic device");
        headphones.setPrice(50.50).setStock(2);

        ItemRepository itemRepository = new ItemRepository();
        ItemMapper itemMapper = new ItemMapper();
        ItemDTO headphonesDTO = itemMapper.itemToItemDTO(headphones);
        ItemService itemService = new ItemService(itemMapper, itemRepository);

        itemService.addItem(headphonesDTO);

        OrderRepository orderRepository = new OrderRepository();
        OrderMapper orderMapper = new OrderMapper();
        OrderService orderService = new OrderService(orderMapper, orderRepository, itemService);

        //when
        Order order = orderService.addItemsToNewOrder("Buying 3 pairs of Headphones", headphonesDTO, 3);
        OrderDTO orderDTO = orderMapper.orderToOrderDTO(order);

        orderService.confirmOrder(orderDTO);
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

        Item headphones = new Item("Headphones", "portable sound listening electronic device");
        headphones.setPrice(50.50).setStock(100);

        ItemMapper itemMapper = new ItemMapper();
        ItemDTO itemDTO = itemMapper.itemToItemDTO(headphones);

        ItemService itemService = new ItemService(itemMapper,new ItemRepository());
        itemService.addItem(itemDTO);

        OrderService orderService = new OrderService(new OrderMapper(),new OrderRepository(),itemService);

        //when / then
        assertThrows(IllegalArgumentException.class, ()-> orderService.addItemsToNewOrder(orderId,itemDTO,5));

    }


}
