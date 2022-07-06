package com.switchfully.eurder.order.service;

import com.switchfully.eurder.itemgroup.api.dto.ItemGroupDTO;
import com.switchfully.eurder.order.api.dto.OrderDTO;
import com.switchfully.eurder.order.domain.Order;
import com.switchfully.eurder.itemgroup.service.ItemGroupMapper;
import com.switchfully.eurder.user.api.dto.UserDTO;
import com.switchfully.eurder.user.service.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OrderMapper {
    UserMapper userMapper;
    ItemGroupMapper itemGroupMapper;

    public Order toEntity(UserDTO userDTO, List<ItemGroupDTO> itemGroupDTOs){
        return new Order(
                userMapper.toEntity(userDTO),
                itemGroupMapper.toEntity(itemGroupDTOs)
        );
    }


    public Order toEntity(OrderDTO orderDTO) {
        return new Order(
                orderDTO.id(),
                userMapper.toEntity(orderDTO.userDTO()),
                itemGroupMapper.toEntity(orderDTO.orderedItems())
        );
    }

    public OrderDTO toDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                userMapper.toDTO(order.getCustomer()),
                itemGroupMapper.toDTO(order.getOrderedItems())
        );
    }

    public List<OrderDTO> listOfOrdertoOrderDTOList(List<Order> orderList) {
        return orderList.stream().map(this::toDTO).toList();
    }
}
