package com.example.bookcart.controller;

import com.example.bookcart.constant.ProductCategory;
import com.example.bookcart.dto.ProductQueryParams;
import com.example.bookcart.dto.ProductRequest;
import com.example.bookcart.model.Product;
import com.example.bookcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 查詢商品
     * @param category
     * @param search
     * @param orderBy 控制商品排序
     * @param sort 控制商品排序
     * @return
     */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort
            )
    {
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        List<Product> productList = productService.getProducts(productQueryParams);
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    /**
     * 取得商品byid
     * @param productId
     * @return
     */
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId)
    {
        Product product = productService.getProductById(productId);
        if (product != null)
            return ResponseEntity.status(HttpStatus.OK).body(product);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * 建立商品
     * @param productRequest
     * @return
     */
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest)
    {
        Integer productId = productService.createProduct(productRequest);
        Product product = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    /**
     * 更新商品內容
     * @param productId
     * @param productRequest
     * @return
     */
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest)
    {
        Product product = productService.getProductById(productId);
        if (product == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        productService.updateProduct(productId,productRequest);
        Product updateProduct = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    /**
     * 刪除商品
     * @param productId
     * @return
     */
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId)
    {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
