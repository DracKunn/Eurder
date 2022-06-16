package com.switchfully.eurder.items;

import java.util.Objects;

public class Item {
    private static final int ZERO = 0;
    private final String name;
    private final String description;
    private double price;
    private int stock;

    public Item(String name, String description) {
        isNotNullOrEmpty(name, "item name");
        isNotNullOrEmpty(description, "item description");
        this.name = name;
        this.description = description;
        this.price = ZERO;
        this.stock = ZERO;

    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Item setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getStock() {
        return stock;
    }

    public Item setStock(int amount) {
        this.stock = amount;
        return this;
    }

    public static void isNotNullOrEmpty(String stringToValidate, String variableFieldName) {
        if (stringToValidate == null || stringToValidate.isBlank()) {
            throw new IllegalArgumentException(variableFieldName + " cannot be empty");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return Double.compare(item.getPrice(), getPrice()) == 0 && getStock() == item.getStock() && getName().equals(item.getName()) && getDescription().equals(item.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getPrice(), getStock());
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
