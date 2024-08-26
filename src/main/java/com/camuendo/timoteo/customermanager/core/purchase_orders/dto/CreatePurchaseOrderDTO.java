package com.camuendo.timoteo.customermanager.core.purchase_orders.dto;

import com.camuendo.timoteo.customermanager.core.purchase_orders.util.ItemQuantityUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreatePurchaseOrderDTO {

    @JsonProperty(required = true)
    private Long clientId;

    @JsonProperty(required = true)
    private List<ItemQuantityUtil> items;

    @JsonProperty(required = true)
    private Float total;

    @JsonProperty(required = true)
    private LocalDateTime date;

}
