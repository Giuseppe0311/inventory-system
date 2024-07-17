package com.project.inventory.app.orders.entity;

public enum OrderStatus {
    // Estados iniciales
    DRAFT,              // Borrador, la orden está siendo creada
    PENDING_APPROVAL,   // Esperando aprobación interna

    // Estados de procesamiento
    APPROVED,           // Aprobada internamente
    ORDERED,            // Pedido realizado al proveedor
    CONFIRMED,          // Confirmada por el proveedor

    // Estados de envío
    PARTIALLY_SHIPPED,  // Parte de los artículos han sido enviados
    SHIPPED,            // Todos los artículos han sido enviados

    // Estados de recepción
    PARTIALLY_RECEIVED, // Parte de los artículos han sido recibidos
    RECEIVED,           // Todos los artículos han sido recibidos

    // Estados finales
    COMPLETED,          // Orden completada y cerrada
    CANCELLED,          // Orden cancelada

    // Estados especiales
    ON_HOLD,            // Orden en espera por alguna razón
    DISPUTED;            // Hay un problema o disputa con la order


    public boolean canTransitionTo(OrderStatus newStatus) {
        return switch (this) {
            case DRAFT -> newStatus == PENDING_APPROVAL || newStatus == CANCELLED;
            case PENDING_APPROVAL -> newStatus == APPROVED || newStatus == CANCELLED || newStatus == ON_HOLD;
            case APPROVED -> newStatus == ORDERED || newStatus == CANCELLED || newStatus == ON_HOLD;
            case ORDERED ->
                    newStatus == SHIPPED || newStatus == PARTIALLY_SHIPPED || newStatus == CANCELLED || newStatus == ON_HOLD;
            case PARTIALLY_SHIPPED, SHIPPED -> newStatus == RECEIVED || newStatus == DISPUTED;
            case RECEIVED -> newStatus == COMPLETED || newStatus == DISPUTED;
            case ON_HOLD ->
                    newStatus == PENDING_APPROVAL || newStatus == APPROVED || newStatus == ORDERED || newStatus == CANCELLED;
            case DISPUTED ->
                    newStatus == SHIPPED || newStatus == RECEIVED || newStatus == CANCELLED || newStatus == ON_HOLD;
            case COMPLETED, CANCELLED -> false; // Estados finales, no pueden transicionar a otros estados
            default -> false;
        };
    }

    public boolean isActive() {
        return this != COMPLETED && this != CANCELLED;
    }

    public boolean canBeCancelled() {
        return this == DRAFT || this == PENDING_APPROVAL || this == APPROVED || this == ORDERED;
    }

    public boolean isAwaitingAction() {
        return this == PENDING_APPROVAL || this == ON_HOLD || this == DISPUTED;
    }

    public boolean isInTransit() {
        return this == PARTIALLY_SHIPPED || this == SHIPPED;
    }
}