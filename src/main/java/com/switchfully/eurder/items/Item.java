package com.switchfully.eurder.items;

import java.util.Objects;
import java.util.logging.Logger;

import static com.switchfully.eurder.util.ValidatorsUtility.*;

public class Item {
//    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private static final int ZERO = 0;
    private final String name;
    private final String description;
    private double price;
    private int stock;

    public Item(String name, String description) {
        isNotNullOrEmpty(description, "item description");
        this.name = validateURLFriendly(name);
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
//        logger.info("The stock for item "+this+" has been set to: "+amount);
        return this;
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
