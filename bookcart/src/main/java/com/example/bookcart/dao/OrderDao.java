package com.example.bookcart.dao;

import com.example.bookcart.model.Order;
import com.example.bookcart.model.OrderItem;

import java.util.List;

public interface OrderDao {
    List<OrderItem> getOrderItemsByOrderId(Integer orderId);
    Order getOrderById(Integer orderId);
    Integer createOrder(Integer userId,Integer totalAmount);
    void createOrderItem(Integer orderId, List<OrderItem> orderItemList);
}
