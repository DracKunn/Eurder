package com.switchfully.eurder.items;

import java.util.Objects;

public class Item {
    private static final int ZERO = 0;
    private final String name;
    private final String description;
    private double price;
    private int amount;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.price = ZERO;
        this.amount = ZERO;

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

    public int getAmount() {
        return amount;
    }

    public Item setAmount(int amount) {
        this.amount = amount;
        return this;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return Double.compare(item.getPrice(), getPrice()) == 0 && getAmount() == item.getAmount() && getName().equals(item.getName()) && getDescription().equals(item.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getPrice(), getAmount());
    }
}
