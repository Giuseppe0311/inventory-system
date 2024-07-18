package com.project.inventory.app.orders.mapper;

import com.project.inventory.app.orders.dto.OrderDTO;
import com.project.inventory.app.orders.entity.Orders;
import com.project.inventory.app.orders.request.OrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrdersMapper {
    Orders  toEntity(OrderRequest orderRequest);
    @Mapping(target = "supplier", source = "supplier.name")
    OrderDTO toDto(Orders orders);
}
