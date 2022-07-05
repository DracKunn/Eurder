package com.switchfully.eurder.order.domain.itemgroup;

import com.switchfully.eurder.item.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ItemGroup {

    private static final int WEEKS_TO_ADD = 1;
    private static final int DAYS_TO_ADD = 1;
    @Id
    private int id;
    @JoinColumn
    @ManyToOne
    private Item selectedItem;
    @Column
    private double priceAtTimeOfOrder;
    @Column
    private int stockAtTimeOfOrder;
    @Column
    private int amount;
    @Column
    private LocalDate shippingDate;

    public ItemGroup(Item selectedItem, int amount) {
        this.selectedItem = selectedItem;
        this.priceAtTimeOfOrder = selectedItem.getPrice();
        this.stockAtTimeOfOrder = selectedItem.getStock();
        this.amount = amount;
        this.shippingDate = calculateShippingDate(amount);
    }

    private LocalDate calculateShippingDate(int amount) {
        if (stockAtTimeOfOrder < amount) {
            return LocalDate.now().plusWeeks(WEEKS_TO_ADD);
        }
        return LocalDate.now().plusDays(DAYS_TO_ADD);
    }


    public void updateAmount(int amountToAdd) {
        this.amount = this.amount + amountToAdd;
        recalculateShippingDate(amount);
    }

    public void recalculateShippingDate(int amount) {
        this.shippingDate = calculateShippingDate(amount);
    }
}
