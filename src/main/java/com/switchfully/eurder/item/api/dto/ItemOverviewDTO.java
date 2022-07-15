package com.switchfully.eurder.item.api.dto;

public record ItemOverviewDTO(int id, String name,String description, double price, int stock, String stockUrgency) {
}
