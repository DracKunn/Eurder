package com.switchfully.eurder.items.orders;

import com.switchfully.eurder.items.*;
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
        OrderService orderService = new OrderService(orderMapper,shoppingBasket , webStock);

        //when
        OrderDTO orderDTO = orderService.addItemsToNewOrder("buying 5 headphones", headphonesDTO, amountOfHeadphonesToBuy);
        Order actual = orderMapper.orderDTOToOrder(orderDTO);
        //then
        Order expected = shoppingBasket.getOrderwithID("buying 5 headphones");
        assertEquals(expected,actual);
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
        OrderService orderService = new OrderService(orderMapper,shoppingBasket , webStock);

        OrderDTO orderDTO = orderService.addItemsToNewOrder("Shopping", headphonesDTO, amountOfHeadphonesToBuy);

        //when
        OrderDTO secondOrderDTO = orderService.addItemsToExistingOrder("Shopping",sweatersDTO,20 );
        Order actual = orderMapper.orderDTOToOrder(secondOrderDTO);
        //then
        assertTrue(shoppingBasket.getOrderMap().containsValue(actual));
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
        warehouse.addItem(headphones);

        ItemMapper itemMapper = new ItemMapper();
        ItemDTO headphonesDTO = itemMapper.itemToItemDTO(headphones);

        ItemService webStock = new ItemService(itemMapper, warehouse);
        OrderService webshop = new OrderService(new OrderMapper(), new OrderRepository(), webStock);

        OrderDTO orderDTO = webshop.addItemsToNewOrder("buying 5 headphones", headphonesDTO, amountOfHeadphonesToBuy);

        //when
        webshop.placeOrder(orderDTO);
        //then
        int actual = headphones.getStock();
        int expected = headphonesStock - amountOfHeadphonesToBuy;
        assertEquals(expected, actual);
    }

}
