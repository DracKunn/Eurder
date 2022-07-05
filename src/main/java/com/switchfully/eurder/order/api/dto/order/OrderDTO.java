package com.switchfully.eurder.order.api.dto.order;

import com.switchfully.eurder.order.api.dto.itemgroup.ItemGroupDTO;
import com.switchfully.eurder.user.api.dto.customer.CustomerDTO;

import java.util.List;


public record OrderDTO(int id, CustomerDTO customerDTO, List<ItemGroupDTO> orderedItems) {
}
