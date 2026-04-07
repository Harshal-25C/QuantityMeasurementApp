package com.quantity.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantityInputDTO {

    @NotNull(message = "quantity1 cannot be null")
    @Valid
    private QuantityDTO quantity1;

    @NotNull(message = "quantity2 cannot be null")
    @Valid
    private QuantityDTO quantity2;

    private String targetUnit;
}