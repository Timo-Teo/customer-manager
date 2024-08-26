package com.camuendo.timoteo.customermanager.core.items.map;

import com.camuendo.timoteo.customermanager.common.util.NullFieldChecker;
import com.camuendo.timoteo.customermanager.core.items.dto.CreateItemDTO;
import com.camuendo.timoteo.customermanager.core.items.dto.UpdateItemDTO;
import com.camuendo.timoteo.customermanager.core.items.entities.Item;
import com.camuendo.timoteo.customermanager.generics.dto.GenericMapperDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MapperItem implements GenericMapperDTO<Item, CreateItemDTO, UpdateItemDTO> {
    @Override
    public Item dtoToEntity(CreateItemDTO createDto) {
        Item item = new Item();
        item.setName(createDto.getName());
        item.setUniqueCode(createDto.getUniqueCode());
        item.setPrice(createDto.getPrice());
        item.setStock(createDto.getStock());

        return item;
    }

    @Override
    public void updateEntityWithDto(Item entity, UpdateItemDTO updateDto) {

        if (NullFieldChecker.areAllFieldsNull(updateDto)) {
            throw new IllegalArgumentException("All fields are null. No update will be performed.");
        }

        Optional.ofNullable(updateDto.getName()).ifPresent(entity::setName);
        Optional.ofNullable(updateDto.getUniqueCode()).ifPresent(entity::setUniqueCode);
        Optional.ofNullable(updateDto.getPrice()).ifPresent(entity::setPrice);
        Optional.ofNullable(updateDto.getStock()).ifPresent(entity::setStock);

    }
}
