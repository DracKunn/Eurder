package com.switchfully.eurder.item.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import java.util.Objects;

import static com.switchfully.eurder.util.validation.ValidatorsUtility.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Table(name = "item", schema = "eurder")
public class Item {
    @Transient
    private static final int ZERO = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_sequence")
    @SequenceGenerator(name = "item_sequence", sequenceName = "item_id_seq", allocationSize = 1)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "stock")
    private int stock;

    public Item(String name, String description) {
        isNotNullOrEmpty(description, "item description");
        this.name = validateURLFriendly(name);
        this.description = description;
        this.price = ZERO;
        this.stock = ZERO;
    }

    public Item(String name, String description, double price, int stock) {
        isNotNullOrEmpty(description, "item description");
        this.name = validateURLFriendly(name);
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = isNotNegative(price, "Price");
    }

    public void setStock(int amount) {
        this.stock = isNotNegative(amount, "Stock");
    }

    public StockUrgency getStockUrgency() {
        if(stock < 5) { return StockUrgency.STOCK_LOW; }
        if (stock < 10) { return StockUrgency.STOCK_MEDIUM; }
        return StockUrgency.STOCK_HIGH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return getId() == item.getId() && Double.compare(item.getPrice(), getPrice()) == 0 && getStock() == item.getStock() && Objects.equals(getName(), item.getName()) && Objects.equals(getDescription(), item.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getPrice(), getStock());
    }

    public enum StockUrgency {
        STOCK_LOW,
        STOCK_MEDIUM,
        STOCK_HIGH;

    }
}
