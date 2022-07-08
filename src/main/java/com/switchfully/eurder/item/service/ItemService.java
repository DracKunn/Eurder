package com.switchfully.eurder.item.service;

import com.switchfully.eurder.item.api.dto.CreateItemDTO;
import com.switchfully.eurder.item.api.dto.ItemDTO;
import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item.domain.ItemRepository;
import com.switchfully.eurder.itemgroup.domain.ItemGroup;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class ItemService {
    private final Logger itemLogger = Logger.getLogger(this.getClass().getName());

    ItemMapper itemMapper;
    ItemRepository itemRepository;


    public ItemDTO addItem(CreateItemDTO createItemDTO) {
        Item item = itemMapper.toEntity(createItemDTO);
        itemRepository.save(item);
        String msg = "The item " + item + " has been added.";
        itemLogger.info(msg);
        return itemMapper.toDTO(item);
    }

    public void removeItemAmountFromStock(ItemGroup itemGroup) {
        Item item = itemGroup.getSelectedItem();
        int amount = itemGroup.getAmount();
        removeAmountFormStock(item, amount);
        String msg = amount + " " + item.getName() + " have been removed from the stock. Current stock: " + item.getStock();
        itemLogger.info(msg);

    }

    private void removeAmountFormStock(Item item, int amount) {
        int itemId = item.getId();
        Item foundItem = getItemById(itemId);
        int stock = foundItem.getStock();
        if (stock < amount) {
            item.setStock(0);
        } else {
            item.setStock(stock - amount);
        }
        itemRepository.save(item);
    }

    @NotNull
    private Item getItemById(int itemId) {
        Item foundItem = itemRepository.findById(itemId).orElse(null);
        if (foundItem == null) {
            throw new IllegalArgumentException("Item not found.");
        }
        return foundItem;
    }


    public ItemDTO getItemDTOById(int itemId) {
        return itemMapper.toDTO(getItemById(itemId));
    }


    public ItemDTO addStock(int itemId, int stockToAdd) {
        Item item = findItemById(itemId);
        int stock = item.getStock() + stockToAdd;
        item.setStock(stock);
        itemRepository.save(item);
        String settingStockMessage = "Updated stock for " + item.getName() + " to: " + stock;
        itemLogger.info(settingStockMessage);
        return itemMapper.toDTO(item);


    }

    private Item findItemById(int itemId) {
        Item foundItem = itemRepository.findById(itemId).orElse(null);
        if (foundItem == null) {
            throw new IllegalArgumentException("Item not found");
        }
        return foundItem;
    }

    public ItemDTO updatePrice(int itemId, double newPrice) {
        Item item = findItemById(itemId);
        item.setPrice(newPrice);
        itemRepository.save(item);
        String priceMessage = "Updated price for " + item.getName() + " to: " + newPrice;
        itemLogger.info(priceMessage);
        return itemMapper.toDTO(item);

    }
}
