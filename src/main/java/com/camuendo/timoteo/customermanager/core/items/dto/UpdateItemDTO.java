package com.camuendo.timoteo.customermanager.core.items.dto;

import lombok.Data;

@Data
public class UpdateItemDTO {
    private String name;
    private String uniqueCode;
    private Float price;
    private Integer stock;
}
