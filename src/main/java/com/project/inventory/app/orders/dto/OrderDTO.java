package com.project.inventory.app.orders.dto;

import com.project.inventory.app.orders.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderDTO(
        Integer id,
        String supplier,
        LocalDateTime orderDate,
        LocalDateTime expectedDeliveryDate,
        OrderStatus status,
        BigDecimal totalAmount,
        String createdBy

) {
}
