package com.switchfully.eurder.order.api.dto.itemgroup;

import com.switchfully.eurder.item.api.dto.ItemDTO;

import java.time.LocalDate;

public record CreateItemGroupDTO(ItemDTO selectedItem, int amount, LocalDate shippingDate) {
}
