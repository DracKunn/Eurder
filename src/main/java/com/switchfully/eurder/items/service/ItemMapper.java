package com.switchfully.eurder.items.service;

import com.switchfully.eurder.items.api.dto.ItemDTO;
import com.switchfully.eurder.items.domain.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
//    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public ItemDTO itemToItemDTO(Item item){
//        logger.info("Item -> DTO: ..." +item);
        return new ItemDTO(item.getName(),item.getDescription(),item.getPrice(),item.getStock());
    }

    public Item itemDTOToItem(ItemDTO itemDTO){
        Item item = new Item(itemDTO.name(),itemDTO.description());
//        logger.info("DTO -> Item: ..." + itemDTO);
        return item.setPrice(itemDTO.price()).setStock(itemDTO.amount());
    }

}
