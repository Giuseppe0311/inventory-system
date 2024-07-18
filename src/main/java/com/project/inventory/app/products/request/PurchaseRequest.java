package com.project.inventory.app.products.request;

public record PurchaseRequest(
        Integer productId,
        Integer quantity
) {
}
