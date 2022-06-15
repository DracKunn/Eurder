package com.switchfully.eurder.items;

import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public ItemDTO itemToItemDTO(Item item){
        return new ItemDTO(item.getName(),item.getDescription(),item.getPrice(),item.getStock());
    }

    public Item itemDTOToItem(ItemDTO itemDTO){
        Item item = new Item(itemDTO.name(),itemDTO.description());
        return item.setPrice(itemDTO.price()).setStock(itemDTO.amount());
    }

}
