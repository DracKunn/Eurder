package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.Item;

import java.time.LocalDate;

public class ItemGroup {

    private static final int WEEKS_TO_ADD = 1;
    private static final int DAYS_TO_ADD = 1;
    private final SelectedItem selectedItem;
    private int amount;
    private LocalDate shippingDate;
    public ItemGroup(Item item, int amount) {
        this.selectedItem = new SelectedItem(item.getName(), item.getDescription(), item.getPrice(), item.getStock());
        this.amount = amount;
        this.shippingDate = calculateShippingDate(amount);

    }

    private LocalDate calculateShippingDate(int amount) {
        int stock = selectedItem.stock();
        if (stock < amount){
            return LocalDate.now().plusWeeks(WEEKS_TO_ADD);
        }
        return LocalDate.now().plusDays(DAYS_TO_ADD);
    }


    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public SelectedItem getSelectedItem() {
        return selectedItem;
    }

    public int getAmount() {
        return amount;
    }

    public void updateAmount(int amountToAdd) {
        this.amount = this.amount + amountToAdd;
        recalculateShippingDate(amount);
    }

    public void recalculateShippingDate(int amount){
        this.shippingDate = calculateShippingDate(amount);
    }
}
