package com.switchfully.eurder.items.domain;

import com.switchfully.eurder.items.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemRepository {

    private final Map<String, Item> itemMap;

    public ItemRepository() {
        this.itemMap = new HashMap<>();
    }

    public void addItem(Item item) {
        itemMap.put(item.getName(), item);
    }

    public Item getItemByName(String name) {
        return itemMap.get(name);
    }
}
