package com.switchfully.eurder.items;

import com.switchfully.eurder.orders.ItemGroup;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ItemService {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    ItemMapper itemMapper;
    ItemRepository itemRepository;

    public ItemService(ItemMapper itemMapper, ItemRepository itemRepository) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    public ItemDTO addItem(ItemDTO itemDTO) {
        Item item = getItemFromDTO(itemDTO);
        itemRepository.addItem(item);
        logger.info("The item " + item + " has been added.");
        return itemDTO;
    }

    public void removeItemAmountFromStock(ItemGroup itemGroup) {
        Item item = itemRepository.getItemByName(itemGroup.getSelectedItem().name());
        int amount = itemGroup.getAmount();
        removeAmountFormStock(item, amount);
        logger.info(amount + " " + item.getName() + " have been removed from the stock. Current stock: " + item.getStock());

    }

    private void removeAmountFormStock(Item item, int amount) {
        String itemName = item.getName();
        int stock = itemRepository.getItemByName(itemName).getStock();
        if (stock < amount) {
            item.setStock(0);
        } else {
            item.setStock(stock - amount);
        }
    }
    public Item getItemFromDTO(ItemDTO itemDTO) {
        return itemMapper.itemDTOToItem(itemDTO);
    }

    public ItemDTO getItemDTOFromItemName(String itemName){
        Item item = itemRepository.getItemByName(itemName);
        return itemMapper.itemToItemDTO(item);
    }

}
