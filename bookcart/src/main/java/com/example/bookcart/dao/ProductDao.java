package com.example.bookcart.dao;

import com.example.bookcart.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
