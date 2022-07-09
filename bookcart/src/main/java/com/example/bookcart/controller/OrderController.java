package com.example.bookcart.controller;

import com.example.bookcart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
}
