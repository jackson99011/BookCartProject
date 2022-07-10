package com.example.bookcart.service;

import com.example.bookcart.dto.CreateOrderRequest;
import com.example.bookcart.dto.OrderQueryParams;
import com.example.bookcart.model.Order;

import java.util.List;

public interface OrderService {
    Integer CreateOrder(Integer userId, CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);
    Integer countOrder(OrderQueryParams orderQueryParams);
    List<Order> getOrders(OrderQueryParams orderQueryParams);
}
