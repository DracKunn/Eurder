package com.switchfully.eurder.items;

import com.switchfully.eurder.items.orders.ItemGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ItemGroupTest {
    @Test
    @DisplayName("given an item and an amount, when creating an item group with an amount less than the stock, a shipping date of 1 day is created")
    void givenAnItemAndAnAmountWhenCreatingAnItemGroupAShippingDateIsCreated() {

        //given
        Item candyCanes = new Item("Candy canes", "Hard sugary candy sticks");
        candyCanes.setPrice(0.05);
        candyCanes.setStock(100);
        //when
        ItemGroup itemGroup = new ItemGroup(candyCanes, 5);
        //then
        LocalDate actual = itemGroup.getShippingDate();
        LocalDate expected = LocalDate.now().plusDays(1);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("given an item and an amount, when creating an item group with an amount less than the stock, a shipping date of 7 days is created")
    void givenAnItemAndAnAmountWhenCreatingAnItemGroupWithAnAmpountLessThanTheStockAShippingDateOfSevenDaysIsCreated() {

        //given
        Item candyCanes = new Item("Candy canes", "Hard sugary candy sticks");
        candyCanes.setPrice(0.05);
        candyCanes.setStock(2);
        //when
        ItemGroup itemGroup = new ItemGroup(candyCanes, 5);
        //then
        LocalDate actual = itemGroup.getShippingDate();
        LocalDate expected = LocalDate.now().plusDays(7);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("given an itemgroup, when the price of the original item changes, the price of the itemgroup does not change")
    void givenAnItemgroupWhenThePriceOfTheOriginalItemChangesThePriceOfTheItemgroupDoesNotChange() {

        //given
        Item candyCanes = new Item("Candy canes", "Hard sugary candy sticks");
        candyCanes.setPrice(0.05);
        candyCanes.setStock(100);
        ItemGroup candyCaneGroup = new ItemGroup(candyCanes, 5);

        //when
        candyCanes.setPrice(2.00);

        //then
        double actual = candyCaneGroup.getSelectedItem().price();
        double expected = 0.05;
        assertEquals(expected,actual);

    }
}
