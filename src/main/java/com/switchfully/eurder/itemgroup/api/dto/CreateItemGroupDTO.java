package com.switchfully.eurder.itemgroup.api.dto;

import com.switchfully.eurder.item.api.dto.ItemDTO;

import java.time.LocalDate;

public record CreateItemGroupDTO(ItemDTO selectedItem, int amount, LocalDate shippingDate) {
}
