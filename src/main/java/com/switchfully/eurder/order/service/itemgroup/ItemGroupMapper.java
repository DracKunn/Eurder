package com.switchfully.eurder.order.service.itemgroup;

import com.switchfully.eurder.item.service.ItemMapper;
import com.switchfully.eurder.order.api.dto.itemgroup.CreateItemGroupDTO;
import com.switchfully.eurder.order.api.dto.itemgroup.ItemGroupDTO;
import com.switchfully.eurder.order.domain.itemgroup.ItemGroup;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ItemGroupMapper {
    ItemMapper itemMapper;

    public ItemGroup toEntity(CreateItemGroupDTO createItemGroupDTO){
        return new ItemGroup(
                itemMapper.toEntity(createItemGroupDTO.selectedItem()),
                createItemGroupDTO.amount()
        );
    }

    public ItemGroup toEntity(ItemGroupDTO itemGroupDTO){
        return new ItemGroup(
                itemGroupDTO.id(),
                itemMapper.toEntity(itemGroupDTO.selectedItem()),
                itemGroupDTO.priceAtTimeOfOrder(),
                itemGroupDTO.stockAtTimeOfOrder(),
                itemGroupDTO.amount(),
                itemGroupDTO.shippingDate()
        );
    }

    public ItemGroupDTO toDTO(ItemGroup itemGroup){
        return new ItemGroupDTO(
                itemGroup.getId(),
                itemMapper.toDTO(itemGroup.getSelectedItem()),
                itemGroup.getPriceAtTimeOfOrder(),
                itemGroup.getStockAtTimeOfOrder(),
                itemGroup.getAmount(),
                itemGroup.getShippingDate());
    }

    public List<ItemGroup> toEntity(List<ItemGroupDTO> itemGroupDTOs) {
        return itemGroupDTOs.stream().map(this::toEntity).toList();
    }

    public List<ItemGroupDTO> toDTO(List<ItemGroup> itemGroups) {
        return itemGroups.stream().map(this::toDTO).toList();
    }

}
