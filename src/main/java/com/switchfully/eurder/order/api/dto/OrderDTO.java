package com.switchfully.eurder.order.api.dto;

import com.switchfully.eurder.itemgroup.api.dto.ItemGroupDTO;
import com.switchfully.eurder.user.api.dto.UserDTO;

import java.util.List;


public record OrderDTO(int id, UserDTO userDTO, List<ItemGroupDTO> orderedItems) {
}
