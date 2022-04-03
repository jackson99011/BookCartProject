package com.example.bookcart.dao;

import com.example.bookcart.dto.ProductRequest;
import com.example.bookcart.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId,ProductRequest productRequest);
}
