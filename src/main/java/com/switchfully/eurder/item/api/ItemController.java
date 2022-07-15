package com.switchfully.eurder.item.api;

import com.switchfully.eurder.item.api.dto.CreateItemDTO;
import com.switchfully.eurder.item.api.dto.ItemOverviewDTO;
import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item.service.ItemService;
import com.switchfully.eurder.item.api.dto.ItemDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("items")
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemOverviewDTO> getAllItems(@RequestParam(name = "stockUrgency", required = false) String stockUrgency) {
        return  itemService.getAllItems(stockUrgency);
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
