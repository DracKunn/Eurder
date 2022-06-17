package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.Item;

import java.util.HashMap;
import java.util.Map;

import static com.switchfully.eurder.util.ValidatorsUtility.*;

public class Order {

    private final String orderId;
    private final Map<String, ItemGroup> orderedItems;

    public Order(String orderId, Map<String, ItemGroup> orderedItems) throws IllegalArgumentException {
        this.orderId = validateStringNoSpace(orderId);
        this.orderedItems = orderedItems;
    }

    public Order(String orderId) throws IllegalArgumentException {
        this.orderId = validateStringNoSpace(orderId);
        this.orderedItems = new HashMap<>();
    }

    public String getOrderId() {
        return orderId;
    }

    public Map<String, ItemGroup> getOrderedItems() {
        return orderedItems;
    }

    public ItemGroup getOrderedItemsByItemName(String itemName) {
        return orderedItems.get(itemName);
    }

    public void addItemToOrder(Item item, int amount) {
        orderedItems.put(item.getName(), new ItemGroup(item, amount));
    }

}
