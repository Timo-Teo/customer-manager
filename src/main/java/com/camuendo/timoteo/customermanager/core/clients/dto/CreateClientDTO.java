package com.camuendo.timoteo.customermanager.core.clients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateClientDTO {

    @NotBlank(message = "Full name is required")
    @JsonProperty(required = true)
    private String name;

    @NotBlank(message = "Last name is required")
    @JsonProperty(required = true)
    private String lastname;

}
