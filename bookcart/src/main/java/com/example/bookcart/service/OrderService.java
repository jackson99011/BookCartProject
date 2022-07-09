package com.example.bookcart.service;

import com.example.bookcart.dto.CreateOrderRequest;

public interface OrderService {
    Integer CreateOrder(Integer userId, CreateOrderRequest createOrderRequest);

}
