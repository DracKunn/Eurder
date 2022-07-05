package com.switchfully.eurder.orders.domain;

import java.util.Objects;

public final class OrderRequest {

    //this might be a CreateOrderDTO
    private int id;
    private final String orderId;
    private final int amount;

    public OrderRequest(String orderId, int amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

    public String orderId() {
        return orderId;
    }

    public int amount() {
        return amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (OrderRequest) obj;
        return Objects.equals(this.orderId, that.orderId) &&
                this.amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, amount);
    }

    @Override
    public String toString() {
        return "OrderRequest[" +
                "orderId=" + orderId + ", " +
                "amount=" + amount + ']';
    }

}
