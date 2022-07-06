package com.switchfully.eurder.order.api.dto;


public record CreateOrderDTO(int customerId, int itemId,int amount) {
}
