package com.switchfully.eurder.items.orders;

import com.switchfully.eurder.items.Item;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private final String orderID;
    private final Map<String, ItemGroup> orderedItems;

    public Order(String orderID, Map<String, ItemGroup> orderedItems) {
        this.orderID = orderID;
        this.orderedItems = orderedItems;
    }

    public Order(String orderID){
        this.orderID = orderID;
        this.orderedItems = new HashMap<>();
    }

    public String getOrderID() {
        return orderID;
    }

    public Map<String, ItemGroup> getOrderedItems() {
        return orderedItems;
    }

    public ItemGroup getOrderedItemsByItemName(String itemName) {
        return orderedItems.get(itemName);
    }

    public void addItemToOrder(Item item, int amount){
        orderedItems.put(item.getName(),new ItemGroup(item,amount));
    }
}
