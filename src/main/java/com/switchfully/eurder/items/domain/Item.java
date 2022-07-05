package com.switchfully.eurder.items.domain;

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
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private double price;
    @Column
    private int stock;

    public Item(String name, String description) {
        isNotNullOrEmpty(description, "item description");
        this.name = validateURLFriendly(name);
        this.description = description;
        this.price = ZERO;
        this.stock = ZERO;
    }

    public Item setPrice(double price) {
        this.price = isNotNegative(price, "Price");
        return this;
    }

    public Item setStock(int amount) {
        this.stock = isNotNegative(amount, "Stock");
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getId() == item.getId() && Double.compare(item.getPrice(), getPrice()) == 0 && getStock() == item.getStock() && Objects.equals(getName(), item.getName()) && Objects.equals(getDescription(), item.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getPrice(), getStock());
    }
}
