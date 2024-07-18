package com.project.inventory.app.products.dto;

import java.math.BigDecimal;

public record PurchaseDTO(
        Integer id,
        Integer name,
        String description,
        String Category,
        String unitMeasure,
        BigDecimal price,
        Integer quantity
) {
}
