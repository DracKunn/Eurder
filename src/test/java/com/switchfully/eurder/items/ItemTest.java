package com.switchfully.eurder.items;

import com.switchfully.eurder.users.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    @Test
    @DisplayName("given a name and descprition when creating item then ok")
    void givenANameAndDescpritionWhenCreatingItemThenOk() {

 //given
    String name = "bottle";
    String description = "receptacle for liquids";
 //when
        Item bottle = new Item(name,description);

 //then
   assertEquals(new Item("bottle", "receptacle for liquids"), bottle);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given a null or blank name when creating an item then throw exception")
    void givenANullOrBlankNameWhenCreatingAnItemThenThrowException(String name) {

 //given

        String description = "receptacle for liquids";
 //when

 //then
        assertThrows(IllegalArgumentException.class, () -> new Item(name, description));

    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given a null or blank description when creating an item then throw exception")
    void givenANullOrBlankDescriptionWhenCreatingAnItemThenThrowException(String description) {

        //given

        String name = "bottle";
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new Item(name, description));

    }

    @Test
    @DisplayName("given an item, when setting the price, the price is changed")
    void givenAnItemWhenSettingThePriceThePriceIsChanged() {

 //given
        String name = "bottle";
        String description = "receptacle for liquids";
        Item bottle = new Item(name,description);

 //when
    bottle.setPrice(0.5);
 //then
   assertEquals(0.5,bottle.getPrice());
    }

    @Test
    @DisplayName("given an item, when setting the stock, the price is changed")
    void givenAnItemWhenSettingThePriceTheStockIsChanged() {

        //given
        String name = "bottle";
        String description = "receptacle for liquids";
        Item bottle = new Item(name,description);

        //when
        bottle.setStock(100);
        //then
        assertEquals(100,bottle.getStock());
    }

}
