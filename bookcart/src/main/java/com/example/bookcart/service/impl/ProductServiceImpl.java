package com.example.bookcart.service.impl;

import com.example.bookcart.dao.ProductDao;
import com.example.bookcart.model.Product;
import com.example.bookcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
