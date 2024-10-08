package com.camuendo.timoteo.customermanager.core.clients.entities;

import com.camuendo.timoteo.customermanager.core.purchase_orders.entity.PurchaseOrder;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @OneToMany(mappedBy = "client")
    @JsonBackReference
    private List<PurchaseOrder> purchaseOrders;
}

