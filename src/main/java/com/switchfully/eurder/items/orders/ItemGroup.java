package com.switchfully.eurder.items.orders;

import com.switchfully.eurder.items.Item;

import java.time.LocalDate;

public class ItemGroup {

    private static final int WEEKS_TO_ADD = 1;
    private static final int DAYS_TO_ADD = 1;
    private final SelectedItem selectedItem;
    private final int amount;
    private final LocalDate shippingDate;
    public ItemGroup(Item item, int amount) {
        this.selectedItem = new SelectedItem(item.getName(), item.getDescription(), item.getPrice(), item.getStock());
        this.amount = amount;
        this.shippingDate = calculateShippingDate(item,amount);

    }

    private LocalDate calculateShippingDate(Item item, int amount) {
        int stock = item.getStock();
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

    // we want to remove the amount that is ordered from the stock of the item. for that we need to get the order, then the map of the ordered items containing a map of ordergroups which will lead to the item...

}
