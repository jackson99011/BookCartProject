package com.example.bookcart.controller;

import com.example.bookcart.constant.ProductCategory;
import com.example.bookcart.dto.CreateOrderRequest;
import com.example.bookcart.dto.OrderQueryParams;
import com.example.bookcart.dto.ProductQueryParams;
import com.example.bookcart.model.Order;
import com.example.bookcart.model.Product;
import com.example.bookcart.service.OrderService;
import com.example.bookcart.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<Order>> getOrders(
            //查詢條件
            @PathVariable Integer userId,
            //分頁 Pagination
            @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    )
    {
        OrderQueryParams orderQueryParams = new OrderQueryParams();
        orderQueryParams.setUserId(userId);
        orderQueryParams.setLimit(limit);
        orderQueryParams.setOffset(offset);
        //取得order List
        List<Order> orderList = orderService.getOrders(orderQueryParams);

        //總數
        Integer count = orderService.countOrder(orderQueryParams);

        //分頁
        Page<Order> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orderList);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @PostMapping("/user/{userId}/order")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest) {
        Integer orderId = orderService.CreateOrder(userId,createOrderRequest);

        Order order = orderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

}
