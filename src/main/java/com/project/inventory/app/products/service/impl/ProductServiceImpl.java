package com.project.inventory.app.products.service.impl;

import com.project.inventory.app.products.dto.PurchaseDTO;
import com.project.inventory.app.products.entity.Products;
import com.project.inventory.app.products.exceptions.ProductNotFoundException;
import com.project.inventory.app.products.repository.ProductRepository;
import com.project.inventory.app.products.request.PurchaseRequest;
import com.project.inventory.app.products.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductsService {
    private final ProductRepository productRepository;
    @Override
    public List<PurchaseDTO> validPurchase(List<PurchaseRequest> purchaseRequests) {
        //TODO: Implement this method
        //this method should return a list of PurchaseDTO  and valid the stock of the products
        //this method only can use and increase the stock when  the order is on status "APPROVED"
        var products = existingProducts(purchaseRequests);
        return null;
    }

    @Override
    public List<Products> existingProducts(List<PurchaseRequest> purchaseRequests) {
        List<Integer> idsProducts = purchaseRequests
                .stream()
                .map(PurchaseRequest::productId)
                .toList();
        List<Products> products = productRepository.findProductsByIdInAndIsActiveTrue(idsProducts);
        if (products.size() != purchaseRequests.size()) {
            throw new ProductNotFoundException("One or more products not found");
        }
        return products;
    }
}
