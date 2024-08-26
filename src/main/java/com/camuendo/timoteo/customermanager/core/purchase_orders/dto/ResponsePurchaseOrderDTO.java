package com.camuendo.timoteo.customermanager.core.purchase_orders.dto;

import com.camuendo.timoteo.customermanager.core.clients.entities.Client;
import com.camuendo.timoteo.customermanager.core.items.dto.ResponseItemDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponsePurchaseOrderDTO {

    private Long id;
    private Client client;
    private String status;
    private LocalDateTime date;
    private String uniqueCode;
    private List<ResponseItemDTO> items;
    private Float total;

}
