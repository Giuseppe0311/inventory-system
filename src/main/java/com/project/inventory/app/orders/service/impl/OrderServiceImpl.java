package com.project.inventory.app.orders.service.impl;

import com.project.inventory.app.orders.dto.OrderDTO;
import com.project.inventory.app.orders.entity.Orders;
import com.project.inventory.app.orders.mapper.OrdersMapper;
import com.project.inventory.app.orders.repository.OrderRepository;
import com.project.inventory.app.orders.request.OrderRequest;
import com.project.inventory.app.orders.service.OrderService;
import com.project.inventory.app.products.dto.PurchaseDTO;
import com.project.inventory.app.products.entity.Products;
import com.project.inventory.app.products.exceptions.AmountException;
import com.project.inventory.app.products.service.ProductsService;
import com.project.inventory.app.suppliers.entity.Suppliers;
import com.project.inventory.app.suppliers.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductsService productsService;
    private final SupplierService supplierService;
    private final OrdersMapper ordersMapper;
    private final OrderRepository orderRepository;

    @Override
    public OrderDTO createDraftOrder(OrderRequest orderRequest) {
       List<PurchaseDTO> products =  productsService.purchaseProducts(orderRequest.purchaseProducts());
       Suppliers  supplier =  supplierService.findSupplierById(orderRequest.supplierId());
        //validate the total Amount
        BigDecimal totalAmount = products.stream()
                .map(purchaseDTO -> purchaseDTO.price().multiply(BigDecimal.valueOf(purchaseDTO.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (totalAmount.compareTo(orderRequest.totalAmount()) != 0){
            throw new AmountException("The total amount is not equal to the sum of the products");
        }
        Orders orders = ordersMapper.toEntity(orderRequest);
        orders.setSupplier(supplier);
        orderRepository.save(orders);
        return ordersMapper.toDto(orders);
    }

    @Override
    public OrderDTO submitForApproval(Integer orderId) {
        return null;
    }

    @Override
    public OrderDTO approveOrder(Integer orderId) {
        return null;
    }

    @Override
    public OrderDTO markOrderAsShiped(Integer orderId) {
        return null;
    }
}
