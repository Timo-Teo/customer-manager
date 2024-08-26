package com.camuendo.timoteo.customermanager.core.items;

import com.camuendo.timoteo.customermanager.core.items.dto.CreateItemDTO;
import com.camuendo.timoteo.customermanager.core.items.dto.UpdateItemDTO;
import com.camuendo.timoteo.customermanager.core.items.entities.Item;
import com.camuendo.timoteo.customermanager.core.items.map.MapperItem;
import com.camuendo.timoteo.customermanager.core.items.repository.ItemRepository;
import com.camuendo.timoteo.customermanager.generics.crud.GenericCRUDServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ItemsServiceImpl extends GenericCRUDServiceImpl<Item, Long, CreateItemDTO, UpdateItemDTO> implements ItemsService {

    public ItemsServiceImpl(ItemRepository repository, MapperItem mapper) {
        super(repository, mapper);
    }

    public void verifyStock(Long id, Integer quantity) {
        Item item = this.findById(id);
        int aux = item.getStock() - quantity;
        if (aux < 0) {
            throw new IllegalArgumentException("Ya no puede comprar este item");
        }
        item.setStock(aux);
        UpdateItemDTO updateItemDTO = new UpdateItemDTO();
        updateItemDTO.setStock(aux);
        super.update(id, updateItemDTO);
    }
}
