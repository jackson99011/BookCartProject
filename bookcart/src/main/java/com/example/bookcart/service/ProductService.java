package com.example.bookcart.service;

import com.example.bookcart.dto.ProductRequest;
import com.example.bookcart.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
}
