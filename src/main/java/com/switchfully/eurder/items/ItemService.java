package com.switchfully.eurder.items;

import com.switchfully.eurder.items.orders.ItemGroup;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    ItemMapper itemMapper;
    ItemRepository itemRepository;

    public ItemService(ItemMapper itemMapper, ItemRepository itemRepository) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    public void addItem(ItemDTO itemDTO) {
        Item item = itemMapper.itemDTOToItem(itemDTO);
        itemRepository.addItem(item);
    }

    public void removeItemgroupAmountFromStock(ItemGroup itemGroup) {
        Item item = itemRepository.getItemByName(itemGroup.getSelectedItem().name());
        int amount = itemGroup.getAmount();
        itemRepository.getItemByName(item.getName()).setStock(removeAmountFormStock(item, amount));

    }

    private int removeAmountFormStock(Item item, int amount) {

        if (item.getStock() < amount) {
            return 0;
        }
        return item.getStock() - amount;
    }

    public Item ItemDTOToItem(ItemDTO itemDTO) {
        return itemMapper.itemDTOToItem(itemDTO);
    }

}
