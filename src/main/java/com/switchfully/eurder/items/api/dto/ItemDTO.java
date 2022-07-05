package com.switchfully.eurder.items.api.dto;

import java.util.Objects;

public final class ItemDTO {
    private final int id;
    private final String name;
    private final String description;
    private final double price;
    private final int amount;

    public ItemDTO(int id,String name, String description, double price, int amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
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

    public int amount() {
        return amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ItemDTO) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.description, that.description) &&
                Double.doubleToLongBits(this.price) == Double.doubleToLongBits(that.price) &&
                this.amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, amount);
    }

    @Override
    public String toString() {
        return "ItemDTO[" +
                "name=" + name + ", " +
                "description=" + description + ", " +
                "price=" + price + ", " +
                "amount=" + amount + ']';
    }

}
