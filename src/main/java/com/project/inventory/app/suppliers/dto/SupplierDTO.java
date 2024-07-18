package com.project.inventory.app.suppliers.dto;

public record SupplierDTO (
        Integer id,
        String name,
        String address,
        String contact,
        String email,
        String phone
) {
}
