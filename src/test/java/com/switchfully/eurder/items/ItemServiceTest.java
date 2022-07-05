package com.switchfully.eurder.items;

import com.switchfully.eurder.items.api.dto.ItemDTO;
import com.switchfully.eurder.items.domain.Item;
import com.switchfully.eurder.items.domain.ItemRepository;
import com.switchfully.eurder.items.service.ItemMapper;
import com.switchfully.eurder.items.service.ItemService;
import com.switchfully.eurder.orders.domain.ItemGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {
    @Test
    @DisplayName("given an item, when adding the item, then the item is in the repository")
    void givenAnItemWhenAddingTheItemThenTheItemIsInTheRepository() {

        //given
        String itemName = "Banana";
        Item banana = new Item(itemName, "yellow, curvy, tasty");
        banana.setPrice(1).setStock(20);

        ItemRepository itemRepository = new ItemRepository();
        ItemMapper itemMapper = new ItemMapper();
        ItemDTO bananaDTO = itemMapper.itemToItemDTO(banana);
        ItemService itemService = new ItemService(itemMapper, itemRepository);
        //when
        itemService.addItem(bananaDTO);
        //then
        Item actual = itemRepository.getItemByName(itemName);
        assertEquals(banana,actual);

    }

    @Test
    @DisplayName("given an item when we remove some stock the stock is removed")
    void givenAnItemWhenWeRemoveSomeStockTheStockIsRemoved() {
        //given
        int stock = 20;
        int amount = 5;
        String itemName = "Banana";
        Item banana = new Item(itemName, "yellow, curvy, tasty");
        banana.setPrice(1).setStock(stock);

        ItemRepository itemRepository = new ItemRepository();
        ItemMapper itemMapper = new ItemMapper();
        ItemDTO bananaDTO = itemMapper.itemToItemDTO(banana);
        ItemService itemService = new ItemService(itemMapper, itemRepository);

        itemService.addItem(bananaDTO);

        ItemGroup bananaToShip = new ItemGroup(banana, amount);

        //when
        itemService.removeItemAmountFromStock(bananaToShip);
        //then
        int actual = itemRepository.getItemByName(itemName).getStock();
        int expected = stock-amount;
        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("given an item and stock, when we remove more stock than there is, stock is set to 0")
    void givenAnItemAndStockWhenWeRemoveMoreStockThanThereIsStockIsSetTo0() {

        //given
        int stock = 100;
        int amount = 500;
        String itemName = "Banana";
        Item banana = new Item(itemName, "yellow, curvy, tasty");
        banana.setPrice(1).setStock(stock);

        ItemRepository itemRepository = new ItemRepository();
        ItemMapper itemMapper = new ItemMapper();
        ItemDTO bananaDTO = itemMapper.itemToItemDTO(banana);
        ItemService itemService = new ItemService(itemMapper, itemRepository);

        itemService.addItem(bananaDTO);

        ItemGroup bananaToShip = new ItemGroup(banana, amount);

        //when
        itemService.removeItemAmountFromStock(bananaToShip);
        //then
        int actual = itemRepository.getItemByName(itemName).getStock();
        assertEquals(0,actual);

    }

}
