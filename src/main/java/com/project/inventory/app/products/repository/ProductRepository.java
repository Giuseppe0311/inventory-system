package com.project.inventory.app.products.repository;

import com.project.inventory.app.products.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products,Integer> {
    List<Products> findProductsByIdInAndIsActiveTrue(List<Integer> ids);
}
