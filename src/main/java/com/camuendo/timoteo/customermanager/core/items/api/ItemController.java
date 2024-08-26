package com.camuendo.timoteo.customermanager.core.items.api;

import com.camuendo.timoteo.customermanager.core.items.ItemsServiceImpl;
import com.camuendo.timoteo.customermanager.core.items.dto.CreateItemDTO;
import com.camuendo.timoteo.customermanager.core.items.dto.UpdateItemDTO;
import com.camuendo.timoteo.customermanager.core.items.entities.Item;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemsServiceImpl itemsService;

    public ItemController(ItemsServiceImpl itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAll() {
        return ResponseEntity.ok(this.itemsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.itemsService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody @Valid CreateItemDTO item) {
        return ResponseEntity.ok(this.itemsService.save(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(
            @PathVariable Long id,
            @RequestBody @Valid UpdateItemDTO item) {
        return ResponseEntity.ok(this.itemsService.update(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        this.itemsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
