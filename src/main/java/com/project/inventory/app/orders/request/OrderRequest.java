package com.project.inventory.app.orders.request;

import com.project.inventory.app.orders.entity.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderRequest(
        @NotNull
        Integer supplierId,
        @NotNull
        LocalDateTime expectedDeliveryDate,
        @Valid
        @NotNull
        List<PurchaseRequest> purchaseProducts,
        @NotNull
        @NotBlank
        OrderStatus status,
        @NotNull
        BigDecimal totalAmount,
        @NotNull
        Integer createdBy
) {
}
