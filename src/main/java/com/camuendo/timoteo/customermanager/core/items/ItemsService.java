package com.camuendo.timoteo.customermanager.core.items;

import com.camuendo.timoteo.customermanager.core.items.dto.CreateItemDTO;
import com.camuendo.timoteo.customermanager.core.items.dto.UpdateItemDTO;
import com.camuendo.timoteo.customermanager.core.items.entities.Item;

import java.util.List;

public interface ItemsService {

    List<Item> findAll();

    Item findById(Long id);

    Item save(CreateItemDTO item);

    Item update(Long id, UpdateItemDTO item);

    void delete(Long id);

}
