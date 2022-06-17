package com.switchfully.eurder.items.orders;

import com.switchfully.eurder.items.Item;
import com.switchfully.eurder.orders.Order;
import com.switchfully.eurder.users.Address;
import com.switchfully.eurder.users.Name;
import com.switchfully.eurder.users.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    static Address address = new Address("Volderstraat", 12, "9000", "Gent");
    static Customer sarah = new Customer("sarah", new Name("Sarah", "Pout"), "sarah.pout@hotmail.com", address, "+32475693215");

    @Test
    @DisplayName("given an order with 2 items, when using the item name to find the item I can see how many I ordered. ")
    void givenAnOrderWith2ItemsWhenUsingTheItemNameToFindTheItemICanSeeHowManyIOrdered() {

        //given

        int amountOfSweatersToOrder = 10;
        Item headphones = new Item("Headphones", "portable sound listening electronic device");
        headphones.setPrice(50.50).setStock(50);
        Item sweaters = new Item("Sweater", "Warm clothing covering the torso and arms");
        sweaters.setPrice(25.75).setStock(100);


        Order order = new Order(sarah, "Bestelling");
        order.addItemToOrder(headphones, 5);
        order.addItemToOrder(sweaters, amountOfSweatersToOrder);
        //when
        int actual = order.getOrderedItemsByItemName("Sweater").getAmount();
        //then

        assertEquals(amountOfSweatersToOrder, actual);
    }

    @Test
    @DisplayName("given an order id with a space in the name, when creating the order, then throw illegal argument exception")
    void givenAnOrderIdWithASpaceInTheNameWhenCreatingTheOrderThenThrowIllegalArgumentException() {

        //given
        String orderId = "twee woorden";
        //when / then
        assertThrows(IllegalArgumentException.class, () -> new Order(sarah,orderId));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given an order id that is null or empty, when creating a new order, then an illegal argument exception is thrown")
    void givenAnOrderIdThatIsNullOrEmpty_whenCreatingANewOrder_thenAnExceptionIsThrown(String orderId) {

        assertThrows(IllegalArgumentException.class, () -> new Order(sarah,orderId));

    }
}
