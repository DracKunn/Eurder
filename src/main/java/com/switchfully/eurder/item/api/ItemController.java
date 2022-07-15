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
    private final java.util.logging.Logger logger = Logger.getLogger(this.getClass().getName());
    ItemService itemService;

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO addItem(@RequestBody CreateItemDTO createItemDTO){
        logger.info("Added item " + createItemDTO + ".");
        return this.itemService.addItem(createItemDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemOverviewDTO> getAllItems(@RequestParam(name = "stockUrgency", required = false) String stockUrgency) {
        return  itemService.getAllItems(stockUrgency);
    }




}
