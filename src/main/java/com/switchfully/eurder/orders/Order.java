package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Order {
    public static final String OWASP_ORDERID_VALIDATION = "'\s'";

    private final String orderId;
    private final Map<String, ItemGroup> orderedItems;

    public Order(String orderId, Map<String, ItemGroup> orderedItems) {
        this.orderId = validateOrderId(orderId);
        this.orderedItems = orderedItems;
    }

    public Order(String orderId){
        this.orderId = validateOrderId(orderId);
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

    public void addItemToOrder(Item item, int amount){
        orderedItems.put(item.getName(),new ItemGroup(item,amount));
    }

    private String validateOrderId(String orderId)throws IllegalArgumentException{
        isNotNullOrEmpty(orderId,"order ID");
        Pattern pattern = Pattern.compile(OWASP_ORDERID_VALIDATION);
        Matcher matcher = pattern.matcher(orderId);
        if (!matcher.matches()) {

            throw new IllegalArgumentException("invalid order ID format");
        }
        return orderId;

    }

    public static void isNotNullOrEmpty(String stringToValidate, String variableFieldName) throws IllegalArgumentException{
        if (stringToValidate == null || stringToValidate.isBlank()) {
            throw new IllegalArgumentException(variableFieldName + " cannot be empty");
        }
    }
}
