package com.switchfully.eurder.order.api.dto.order;


public record CreateOrderDTO(int customerId, int itemId,int amount) {
}
