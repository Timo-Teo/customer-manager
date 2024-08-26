package com.camuendo.timoteo.customermanager.core.items.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateItemDTO {

    @NotBlank(message = "Name is required")
    @JsonProperty(required = true)
    private String name;

    @NotBlank(message = "Unique code is required")
    @JsonProperty(required = true)
    private String uniqueCode;

    @JsonProperty(required = true)
    private Float price;

    @JsonProperty(required = true)
    private Integer stock;

}
