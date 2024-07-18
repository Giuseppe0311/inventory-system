package com.project.inventory.app.suppliers.repository;


import com.project.inventory.app.suppliers.entity.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Suppliers,Integer> {
}
