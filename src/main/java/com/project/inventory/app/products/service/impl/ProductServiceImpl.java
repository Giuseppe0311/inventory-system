package com.project.inventory.app.products.service.impl;

import com.project.inventory.app.products.dto.PurchaseDTO;
import com.project.inventory.app.products.entity.Products;
import com.project.inventory.app.products.exceptions.ProductNotFoundException;
import com.project.inventory.app.products.mapper.ProductMapper;
import com.project.inventory.app.products.repository.ProductRepository;
import com.project.inventory.app.orders.request.PurchaseRequest;
import com.project.inventory.app.products.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductsService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<PurchaseDTO> purchaseProducts(List<PurchaseRequest> purchaseRequests) {
        var purchaseDTO = new ArrayList<PurchaseDTO>();
        List<Products> products = existingProducts(purchaseRequests);

        for (Products product : products) {
            PurchaseRequest purchaseRequest = findPurchaseRequestForProduct(purchaseRequests, product);
            purchaseDTO.add(productMapper.toPurchaseDTO(product, purchaseRequest.quantity()));
        }

        return purchaseDTO;
    }

    @Override
    @Transactional
    public List<PurchaseDTO> validPurchase(List<PurchaseRequest> purchaseRequests) {
        var purchaseDTO = new ArrayList<PurchaseDTO>();
        List<Products> products = existingProducts(purchaseRequests);

        for (Products product : products) {
            PurchaseRequest purchaseRequest = findPurchaseRequestForProduct(purchaseRequests, product);
            updateStock(product, purchaseRequest);
            purchaseDTO.add(productMapper.toPurchaseDTO(product, purchaseRequest.quantity()));
        }

        return purchaseDTO;
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

    private PurchaseRequest findPurchaseRequestForProduct(List<PurchaseRequest> purchaseRequests, Products product) {
        return purchaseRequests
                .stream()
                .filter(purchase -> purchase.productId().equals(product.getId()))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found in request"));
    }

    private void updateStock(Products product, PurchaseRequest purchaseRequest) {
        Integer newStock = product.getMinimumStock() - purchaseRequest.quantity();
        product.setMinimumStock(newStock);
        productRepository.save(product);
    }
}
