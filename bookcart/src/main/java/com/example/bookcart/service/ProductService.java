package com.example.bookcart.service;

import com.example.bookcart.constant.ProductCategory;
import com.example.bookcart.dto.ProductQueryParams;
import com.example.bookcart.dto.ProductRequest;
import com.example.bookcart.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId,ProductRequest productRequest);
    void deleteProduct(Integer productId);
}
