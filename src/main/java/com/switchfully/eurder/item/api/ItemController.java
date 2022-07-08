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
    private final java.util.logging.Logger itemControllerLogger = Logger.getLogger(this.getClass().getName());
    ItemService itemService;

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO addItem(@RequestBody CreateItemDTO createItemDTO) {
        String msg = "Added item " + createItemDTO + ".";
        itemControllerLogger.info(msg);
        return this.itemService.addItem(createItemDTO);
    }

    @PostMapping(path = "{itemId}/add-stock", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ItemDTO addStock(@RequestBody int stockToAdd, @RequestParam int itemId) {
        String message = "Adding " + stockToAdd + " units of item with id " + itemId + "to stock.";
        itemControllerLogger.info(message);
        return this.itemService.addStock(itemId, stockToAdd);
    }

    @PostMapping(path = "{itemId}/update-price", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ItemDTO updatePrice(@RequestBody double newPrice, @RequestParam int itemId) {
        String message = "Setting price for item with id " + itemId + " to: " + newPrice + ".";
        itemControllerLogger.info(message);
        return this.itemService.updatePrice(itemId,newPrice);
    }


}
