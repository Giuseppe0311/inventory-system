package com.project.inventory.app.products.mapper;

import com.project.inventory.app.products.dto.PurchaseDTO;
import com.project.inventory.app.products.entity.Products;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id" ,source = "products.id" )
    @Mapping(target = "name" ,source = "products.name" )
    @Mapping(target = "description" ,source = "products.description" )
    @Mapping(target = "Category" ,source = "products.category" )
    @Mapping(target = "unitMeasure" ,source = "products.unitMeasure" )
    @Mapping(target = "price" ,source = "products.price" )
    @Mapping(target = "quantity" ,source = "quantity" )
    PurchaseDTO toPurchaseDTO(Products products, Integer quantity);
}
