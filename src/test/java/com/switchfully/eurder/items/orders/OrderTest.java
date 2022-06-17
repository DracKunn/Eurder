package com.switchfully.eurder.items.orders;

import com.switchfully.eurder.items.Item;
import com.switchfully.eurder.orders.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    @Test
    @DisplayName("given an order with 2 items, when using the item name to find the item I can see how many I ordered. ")
    void givenAnOrderWith2ItemsWhenUsingTheItemNameToFindTheItemICanSeeHowManyIOrdered() {

        //given
        int amountOfSweatersToOrder = 10;
        Item headphones = new Item("Headphones", "portable sound listening electronic device");
        headphones.setPrice(50.50).setStock(50);
        Item sweaters = new Item("Sweater", "Warm clothing covering the torso and arms");
        sweaters.setPrice(25.75).setStock(100);

        Order order = new Order("Bestelling");
        order.addItemToOrder(headphones, 5);
        order.addItemToOrder(sweaters, amountOfSweatersToOrder);
        //when
        int actual = order.getOrderedItemsByItemName("Sweater").getAmount();
        //then

        assertEquals(amountOfSweatersToOrder, actual);
    }
}
