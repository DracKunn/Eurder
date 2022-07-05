package com.switchfully.eurder.item.api;

import com.switchfully.eurder.item.api.dto.CreateItemDTO;
import com.switchfully.eurder.item.service.ItemService;
import com.switchfully.eurder.item.api.dto.ItemDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@AllArgsConstructor
@RequestMapping("items")
public class ItemController {
    private final java.util.logging.Logger logger = Logger.getLogger(this.getClass().getName());
    ItemService itemService;

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO addItem(@RequestBody CreateItemDTO createItemDTO){
        logger.info("Added item " + createItemDTO + ".");
        return this.itemService.addItem(createItemDTO);
    }


}
