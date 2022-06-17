package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.Item;
import com.switchfully.eurder.users.customer.Customer;

import java.util.HashMap;
import java.util.Map;

import static com.switchfully.eurder.util.ValidatorsUtility.*;

public class Order {
    private final Customer customer;
    private final String orderId;
    private final Map<String, ItemGroup> orderedItems;

    public Order(Customer customer,String orderId, Map<String, ItemGroup> orderedItems) throws IllegalArgumentException {
        isNotNull(customer, "customer");
        this.customer = customer;
        this.orderId = validateURLFriendly(orderId);
        this.orderedItems = orderedItems;
    }

    public Order(Customer customer,String orderId) throws IllegalArgumentException {
        isNotNull(customer, "customer");
        this.customer = customer;
        this.orderId = validateURLFriendly(orderId);
        this.orderedItems = new HashMap<>();


    }

    public Customer getCustomer() {
        return customer;
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
