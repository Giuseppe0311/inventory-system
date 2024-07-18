package com.project.inventory.app.orders.repository;

import com.project.inventory.app.orders.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
}
