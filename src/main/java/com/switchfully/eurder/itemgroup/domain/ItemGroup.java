package com.switchfully.eurder.itemgroup.domain;

import com.switchfully.eurder.item.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "itemgroup", schema = "eurder")
@AllArgsConstructor
@NoArgsConstructor
public class ItemGroup {

    private static final int WEEKS_TO_ADD = 1;
    private static final int DAYS_TO_ADD = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemgroup_sequence")
    @SequenceGenerator(name = "itemgroup_sequence", sequenceName = "itemgroup_id_seq", allocationSize = 1)
    private int id;
    @JoinColumn(name = "fk_item_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Item selectedItem;
    @Column(name = "price_order")
    private double priceAtTimeOfOrder;
    @Column(name = "stock_order")
    private int stockAtTimeOfOrder;
    @Column(name = "order_amount")
    private int amount;
    @Column(name = "shipping_date")
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
