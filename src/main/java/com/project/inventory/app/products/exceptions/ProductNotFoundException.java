package com.project.inventory.app.products.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductNotFoundException extends RuntimeException {
    private final String message;
}
