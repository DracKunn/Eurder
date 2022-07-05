package com.switchfully.eurder.items.api;

import com.switchfully.eurder.items.service.ItemService;
import com.switchfully.eurder.items.api.dto.ItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("items")
public class ItemController {
    private final java.util.logging.Logger logger = Logger.getLogger(this.getClass().getName());

    ItemService itemService;

    public ItemController(ItemService itemService){this.itemService = itemService;}



    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO addItem(@RequestBody ItemDTO itemDTO){
        logger.info("Added item " + itemDTO + ".");
        return this.itemService.addItem(itemDTO);
    }


}
