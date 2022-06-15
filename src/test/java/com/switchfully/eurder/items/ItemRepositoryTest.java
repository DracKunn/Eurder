package com.switchfully.eurder.items;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    @Test
    @DisplayName("given an item repository when adding an item then item is in repository")
    void givenAnItemRepositoryWhenAddingAnItemThenItemIsInRepository() {

        //given
        ItemRepository itemRepository = new ItemRepository();
        String name = "Socks";
        String description = "Fuzzy fabric to keep your feet warm";
        double price = 7.99;
        int amount = 120;
        Item item = new Item(name, description);
        item.setAmount(120).setPrice(7.99);

        //when
        itemRepository.addItem(item);
        Item actual = itemRepository.getItemByName(name);
        //then
        Item expected = new Item("Socks", "Fuzzy fabric to keep your feet warm");
        expected.setPrice( 7.99).setAmount(120);
        assertEquals(expected, actual);

    }

}
