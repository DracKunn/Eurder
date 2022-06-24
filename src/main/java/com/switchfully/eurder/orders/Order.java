package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.Item;
import com.switchfully.eurder.users.customer.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return getCustomer().equals(order.getCustomer()) && getOrderId().equals(order.getOrderId()) && Objects.equals(getOrderedItems(), order.getOrderedItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), getOrderId(), getOrderedItems());
    }
}
