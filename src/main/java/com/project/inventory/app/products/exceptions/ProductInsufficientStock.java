package com.project.inventory.app.products.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductInsufficientStock  extends RuntimeException{
    private final String message;
}
