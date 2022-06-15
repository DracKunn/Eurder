package com.switchfully.eurder.items.orders;

import com.switchfully.eurder.items.orders.ItemGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Order {
    private final UUID orderID;
    private final Map<String, ItemGroup> orderedItems;

    public Order() {
        this.orderID = UUID.randomUUID();
        this.orderedItems = new HashMap<>();
    }


}
