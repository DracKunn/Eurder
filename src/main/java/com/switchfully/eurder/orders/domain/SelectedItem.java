package com.switchfully.eurder.orders.domain;

import java.util.Objects;

public final class SelectedItem {

    //selected item is used to generate an item group before ordering, so the price and stock can no longer change.
    //It's an in-memory item I think, no need to save in the database
    private final String name;
    private final String description;
    private final double price;
    private final int stock;

    public SelectedItem(String name, String description, double price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public double price() {
        return price;
    }

    public int stock() {
        return stock;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (SelectedItem) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.description, that.description) &&
                Double.doubleToLongBits(this.price) == Double.doubleToLongBits(that.price) &&
                this.stock == that.stock;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, stock);
    }

    @Override
    public String toString() {
        return "SelectedItem[" +
                "name=" + name + ", " +
                "description=" + description + ", " +
                "price=" + price + ", " +
                "stock=" + stock + ']';
    }


}
