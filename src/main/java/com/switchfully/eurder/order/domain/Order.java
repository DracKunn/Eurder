package com.switchfully.eurder.order.domain;

import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.itemgroup.domain.ItemGroup;
import com.switchfully.eurder.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static com.switchfully.eurder.util.validation.ValidatorsUtility.isNotNull;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order", schema = "eurder")
@Getter
public class Order {
    // we need to change the order ID to an int ID.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_id_seq", allocationSize = 1)
    private int id;
    @JoinColumn(name = "fk_customer_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private User customer;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_order")
    private List<ItemGroup> orderedItems;

    public Order(User customer, List<ItemGroup> orderedItems) throws IllegalArgumentException {
        isNotNull(customer, "customer");
        this.customer = customer;
        this.orderedItems = orderedItems;
    }


    public void addItemToOrder(Item item, int amount) {
        orderedItems.add(new ItemGroup(item, amount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return getId() == order.getId() && Objects.equals(getCustomer(), order.getCustomer()) && Objects.equals(getOrderedItems(), order.getOrderedItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomer(), getOrderedItems());
    }
}
