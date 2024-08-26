package com.camuendo.timoteo.customermanager.core.items.entities;

import com.camuendo.timoteo.customermanager.core.purchase_orders.entity.PurchaseOrder;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unique_code")
    private String uniqueCode;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Float price;

    @Column(name = "stock")
    private Integer stock;

    @ManyToMany(mappedBy = "items")
    @JsonBackReference
    private List<PurchaseOrder> purchaseOrders;
}
