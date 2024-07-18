package com.project.inventory.app.orders.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull
        @NotBlank
        Integer productId,
        @Positive
        @NotNull
        Integer quantity
) {
}
