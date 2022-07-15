package com.switchfully.eurder.item.service;

import com.switchfully.eurder.item.api.dto.CreateItemDTO;
import com.switchfully.eurder.item.api.dto.ItemDTO;
import com.switchfully.eurder.item.api.dto.ItemOverviewDTO;
import com.switchfully.eurder.item.domain.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public Item toEntity(CreateItemDTO createItemDTO){
        return new Item(createItemDTO.name(), createItemDTO.description(),createItemDTO.price(),createItemDTO.stock());
    }

    public Item toEntity(ItemDTO itemDTO){
       return new Item(itemDTO.id(),itemDTO.name(),itemDTO.description(),itemDTO.price(),itemDTO.stock());
    }

    public Item toEntity(ItemOverviewDTO itemOverviewDTO){
        return new Item(itemOverviewDTO.id(),itemOverviewDTO.name(),itemOverviewDTO.description(),itemOverviewDTO.price(),itemOverviewDTO.stock());
    }

    public ItemDTO toDTO(Item item){
        return new ItemDTO(item.getId(),item.getName(),item.getDescription(),item.getPrice(),item.getStock());
    }

    public ItemOverviewDTO toItemOverviewDTO(Item item){
        return new ItemOverviewDTO(item.getId(),item.getName(),item.getDescription(),item.getPrice(),item.getStock(),item.getStockUrgency().name());
    }



}
