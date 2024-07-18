package com.project.inventory.app.orders.service;

import com.project.inventory.app.orders.dto.OrderDTO;
import com.project.inventory.app.orders.request.OrderRequest;

public interface OrderService {
    OrderDTO createDraftOrder(OrderRequest orderRequest);
    OrderDTO submitForApproval(Integer orderId);
    OrderDTO approveOrder(Integer orderId);
    OrderDTO markOrderAsShiped(Integer orderId);
//    OrderDTO receiveOrder(Integer orderId,List<ProductReceived> productsReceived);
}
