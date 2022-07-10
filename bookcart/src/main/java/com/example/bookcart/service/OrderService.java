package com.example.bookcart.service;

import com.example.bookcart.dto.CreateOrderRequest;
import com.example.bookcart.model.Order;

public interface OrderService {
    Integer CreateOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);
}
