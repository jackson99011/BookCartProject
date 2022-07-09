package com.example.bookcart.dao;

import com.example.bookcart.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Integer createOrder(Integer userId,Integer totalAmount);
    void createOrderItem(Integer orderId, List<OrderItem> orderItemList);
}
