package com.camuendo.timoteo.customermanager.core.purchase_orders.api;

import com.camuendo.timoteo.customermanager.core.purchase_orders.PurchaseOrderServiceImpl;
import com.camuendo.timoteo.customermanager.core.purchase_orders.dto.CreatePurchaseOrderDTO;
import com.camuendo.timoteo.customermanager.core.purchase_orders.dto.ResponsePurchaseOrderDTO;
import com.camuendo.timoteo.customermanager.core.purchase_orders.dto.UpdatePurchaseOrderDTO;
import com.camuendo.timoteo.customermanager.core.purchase_orders.entity.PurchaseOrder;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderServiceImpl purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderServiceImpl purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @RequestMapping
    public ResponseEntity<List<PurchaseOrder>> getAll() {
        return ResponseEntity.ok(this.purchaseOrderService.findAll());
    }

    @RequestMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.purchaseOrderService.findById(id));
    }

    @RequestMapping("/unique-code")
    public ResponseEntity<ResponsePurchaseOrderDTO> getByUniqueCode(@RequestParam String code) {
        return ResponseEntity.ok(this.purchaseOrderService.findByUniqueCode(code));
    }

    @RequestMapping("/client-id/{clientId}")
    public ResponseEntity<List<PurchaseOrder>> getByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(this.purchaseOrderService.findAllByClientId(clientId));
    }

    @PostMapping
    public ResponseEntity<ResponsePurchaseOrderDTO> create(@RequestBody @Valid CreatePurchaseOrderDTO createPurchaseOrderDTO) {
        return ResponseEntity.ok(this.purchaseOrderService.save(createPurchaseOrderDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrder> update(@PathVariable Long id, @RequestBody @Valid UpdatePurchaseOrderDTO updatePurchaseOrderDTO) {
        return ResponseEntity.ok(this.purchaseOrderService.update(id, updatePurchaseOrderDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.purchaseOrderService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
