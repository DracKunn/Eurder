package com.switchfully.eurder.items;

import com.switchfully.eurder.items.domain.Item;
import com.switchfully.eurder.items.domain.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        item.setStock(120).setPrice(7.99);

        //when
        itemRepository.addItem(item);
        Item actual = itemRepository.getItemByName(name);
        //then
        Item expected = new Item("Socks", "Fuzzy fabric to keep your feet warm");
        expected.setPrice( 7.99).setStock(120);
        assertEquals(expected, actual);

    }

}
