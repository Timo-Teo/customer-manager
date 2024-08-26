package com.camuendo.timoteo.customermanager.core.purchase_orders.repository;

import com.camuendo.timoteo.customermanager.core.purchase_orders.entity.PurchaseOrder;
import com.camuendo.timoteo.customermanager.generics.repositories.GenericRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderRepository extends GenericRepository<PurchaseOrder, Long> {

    Optional<PurchaseOrder> findPurchaseOrderByUniqueCode(String uniqueCode);

    List<PurchaseOrder> findAllByClientId(Long clientId);

    @Query(value = "SELECT o.id FROM purchase_orders o ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Long lastId();
}
