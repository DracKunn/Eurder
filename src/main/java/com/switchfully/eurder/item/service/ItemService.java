package com.switchfully.eurder.item.service;

import com.switchfully.eurder.item.api.dto.CreateItemDTO;
import com.switchfully.eurder.item.api.dto.ItemDTO;
import com.switchfully.eurder.item.api.dto.ItemOverviewDTO;
import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item.domain.ItemRepository;
import com.switchfully.eurder.itemgroup.domain.ItemGroup;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemService {
    private final Logger itemLogger = Logger.getLogger(this.getClass().getName());

    ItemMapper itemMapper;
    ItemRepository itemRepository;

    @Transactional
    public ItemDTO addItem(CreateItemDTO createItemDTO) {
        Item item = itemMapper.toEntity(createItemDTO);
        itemRepository.save(item);
        itemLogger.info("The item " + item + " has been added.");
        return itemMapper.toDTO(item);
    }

    public void removeItemAmountFromStock(ItemGroup itemGroup) {
        Item item = itemGroup.getSelectedItem();
        int amount = itemGroup.getAmount();
        removeAmountFromStock(item, amount);
        itemLogger.info(amount + " " + item.getName() + " have been removed from the stock. Current stock: " + item.getStock());

    }

    @Transactional(propagation = Propagation.REQUIRED)
    void removeAmountFromStock(Item item, int amount) {
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

    @Transactional(propagation = Propagation.SUPPORTS)
    @NotNull Item getItemById(int itemId) {
        Item foundItem = itemRepository.findById(itemId).orElse(null);
        if (foundItem == null) {
            throw new IllegalArgumentException("Item not found.");
        }
        return foundItem;
    }

    public ItemDTO getItemDTOById(int itemId) {
        return itemMapper.toDTO(getItemById(itemId));
    }

    @Transactional
    public void setStockForItem(Item item, int stock) {
        String settingStockMessage = "Setting stock for " + item + " to amount: " + stock;
        itemLogger.info(settingStockMessage);
        item.setStock(stock);
        itemRepository.save(item);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ItemOverviewDTO> getAllItems(String stockUrgency) {
        List<ItemOverviewDTO> allItems = itemRepository.findAll().stream()
                .map(item -> itemMapper.toItemOverviewDTO(item))
                .collect(Collectors.toList());
        return filterOnStockUrgency(stockUrgency, allItems);
    }


    private List<ItemOverviewDTO> filterOnStockUrgency(String stockUrgency, List<ItemOverviewDTO> allItems) {
        if (stockUrgency != null) {
            Item.StockUrgency stockUrgencyToFilterOn = Item.StockUrgency.valueOf(stockUrgency);
            return allItems.stream()
                    .filter(item -> Item.StockUrgency.valueOf(item.stockUrgency()).equals(stockUrgencyToFilterOn))
                    .collect(Collectors.toList());
        } else {
            return allItems;
        }
    }
}
