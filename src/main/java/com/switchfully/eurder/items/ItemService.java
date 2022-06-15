package com.switchfully.eurder.items;

import org.springframework.stereotype.Service;

@Service
public class ItemService {
    ItemMapper itemMapper;
    ItemRepository itemRepository;

    public ItemService(ItemMapper itemMapper, ItemRepository itemRepository){
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    public void addItem (ItemDTO itemDTO){
        Item item = itemMapper.itemDTOToItem(itemDTO);
        itemRepository.addItem(item);
    }
}
