package com.switchfully.eurder.orders.domain;

import com.switchfully.eurder.items.domain.Item;
import com.switchfully.eurder.users.customer.domain.Customer;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.switchfully.eurder.util.validation.ValidatorsUtility.*;

public class Order {
    // we need to change the order ID to an int ID.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_id_seq", allocationSize = 1)
    private int id;
    private final Customer customer;
    private final Map<String, ItemGroup> orderedItems;

    public Order(Customer customer, Map<String, ItemGroup> orderedItems) throws IllegalArgumentException {
        isNotNull(customer, "customer");
        this.customer = customer;
        this.orderedItems = orderedItems;
    }

    public Order(Customer customer,String orderId) throws IllegalArgumentException {
        isNotNull(customer, "customer");
        this.customer = customer;
        this.orderedItems = new HashMap<>();
    }

    public Customer getCustomer() {
        return customer;
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
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(getCustomer(), order.getCustomer()) && Objects.equals(getOrderedItems(), order.getOrderedItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getCustomer(), getOrderedItems());
    }
}
