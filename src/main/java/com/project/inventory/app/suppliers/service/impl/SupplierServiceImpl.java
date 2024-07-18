package com.project.inventory.app.suppliers.service.impl;

import com.project.inventory.app.suppliers.entity.Suppliers;
import com.project.inventory.app.suppliers.exceptions.SupplierNotFound;
import com.project.inventory.app.suppliers.repository.SupplierRepository;
import com.project.inventory.app.suppliers.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository repository;

    @Override
    public Suppliers findSupplierById(int id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new SupplierNotFound("Supplier not found"));
    }
}
