package com.camuendo.timoteo.customermanager.core.purchase_orders;

import com.camuendo.timoteo.customermanager.core.purchase_orders.dto.CreatePurchaseOrderDTO;
import com.camuendo.timoteo.customermanager.core.purchase_orders.dto.ResponsePurchaseOrderDTO;
import com.camuendo.timoteo.customermanager.core.purchase_orders.dto.UpdatePurchaseOrderDTO;
import com.camuendo.timoteo.customermanager.core.purchase_orders.entity.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {

    List<PurchaseOrder> findAll();

    PurchaseOrder findById(Long id);

    ResponsePurchaseOrderDTO findByUniqueCode(String uniqueCode);

    List<PurchaseOrder> findAllByClientId(Long clientId);

    ResponsePurchaseOrderDTO save(CreatePurchaseOrderDTO createPurchaseOrderDTO);

    PurchaseOrder update(Long id, UpdatePurchaseOrderDTO updatePurchaseOrderDTO);

    void delete(Long id);


}
