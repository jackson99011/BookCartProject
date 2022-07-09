package com.example.bookcart.service.impl;

import com.example.bookcart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderService orderService;
}
