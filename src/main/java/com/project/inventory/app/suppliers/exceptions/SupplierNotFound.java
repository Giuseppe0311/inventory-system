package com.project.inventory.app.suppliers.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SupplierNotFound extends RuntimeException{
    private final String message;
}
