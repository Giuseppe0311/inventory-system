package com.project.inventory.app.products.service;

import com.project.inventory.app.products.dto.PurchaseDTO;
import com.project.inventory.app.products.entity.Products;
import com.project.inventory.app.orders.request.PurchaseRequest;

import java.util.List;

public interface ProductsService {
    List<PurchaseDTO> purchaseProducts(List<PurchaseRequest> purchaseRequests);
    List<PurchaseDTO> validPurchase(List<PurchaseRequest> purchaseRequests);
    List<Products> existingProducts(List<PurchaseRequest> purchaseRequests);

}
