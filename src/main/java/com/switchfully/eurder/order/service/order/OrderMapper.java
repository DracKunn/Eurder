package com.switchfully.eurder.order.service.order;

import com.switchfully.eurder.order.api.dto.itemgroup.ItemGroupDTO;
import com.switchfully.eurder.order.api.dto.order.CreateOrderDTO;
import com.switchfully.eurder.order.api.dto.order.OrderDTO;
import com.switchfully.eurder.order.domain.order.Order;
import com.switchfully.eurder.order.service.itemgroup.ItemGroupMapper;
import com.switchfully.eurder.user.api.dto.customer.CustomerDTO;
import com.switchfully.eurder.user.service.customer.CustomerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OrderMapper {
    CustomerMapper customerMapper;
    ItemGroupMapper itemGroupMapper;

    public Order toEntity(CustomerDTO customerDTO, List<ItemGroupDTO> itemGroupDTOs){
        return new Order(
                customerMapper.toEntity(customerDTO),
                itemGroupMapper.toEntity(itemGroupDTOs)
        );
    }

    public Order toEntity(OrderDTO orderDTO) {
        return new Order(
                orderDTO.id(),
                customerMapper.toEntity(orderDTO.customerDTO()),
                itemGroupMapper.toEntity(orderDTO.orderedItems())
        );
    }

    public OrderDTO toDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                customerMapper.toDTO(order.getCustomer()),
                itemGroupMapper.toDTO(order.getOrderedItems())
        );
    }

    public List<OrderDTO> listOfOrdertoOrderDTOList(List<Order> orderList) {
        return orderList.stream().map(this::toDTO).toList();
    }
}
