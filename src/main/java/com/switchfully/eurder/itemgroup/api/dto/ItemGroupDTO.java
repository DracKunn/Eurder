package com.switchfully.eurder.itemgroup.api.dto;

import com.switchfully.eurder.item.api.dto.ItemDTO;

import java.time.LocalDate;

public record ItemGroupDTO (int id, ItemDTO selectedItem,double priceAtTimeOfOrder,int stockAtTimeOfOrder, int amount, LocalDate shippingDate){

}
