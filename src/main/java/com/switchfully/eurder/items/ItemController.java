package com.switchfully.eurder.items;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class ItemController {
    ItemService itemService;

    public ItemController(ItemService itemService){this.itemService = itemService;}



    @PostMapping(consumes = "application/json")
    public ItemDTO addItem(ItemDTO itemDTO){
        itemService.addItem(itemDTO);
        return itemDTO;
    }
}
