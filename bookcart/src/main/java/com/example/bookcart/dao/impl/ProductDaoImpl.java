package com.example.bookcart.dao.impl;

import com.example.bookcart.constant.ProductCategory;
import com.example.bookcart.dao.ProductDao;
import com.example.bookcart.dto.ProductQueryParams;
import com.example.bookcart.dto.ProductRequest;
import com.example.bookcart.model.Product;
import com.example.bookcart.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "SELECT * FROM product where 1=1";
        Map<String,Object> map = new HashMap<>();
        if (productQueryParams.getCategory() != null) {
            sql += " AND category = :category";
            map.put("category",productQueryParams.getCategory().name());
        }
        if (productQueryParams.getSearch() != null) {
            sql += " AND product_name Like :search";
            map.put("search","%" +  productQueryParams.getSearch() + "%");
        }
        //排序
        sql += " ORDER BY :orderBy :sort";
        map.put("orderBy", productQueryParams.getOrderBy());
        map.put("sort", productQueryParams.getSort());
        //分頁
        sql += " LIMIT :limit OFFSET :offset";
        map.put("limit", productQueryParams.getLimit());
        map.put("offset", productQueryParams.getOffset());

        return namedParameterJdbcTemplate.query(sql, map,new ProductRowMapper());
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT * FROM product where product_id = :productId";
        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        if (productList.size() > 0)
            return  productList.get(0);
        else
            return null;
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product(product_name,category,image_url,price,stock,description," +
                "created_date,last_modified_date)" +
                "VALUES(:productName,:category,:image_url,:price,:stock,:description," +
                ":created_date,:last_modified_date)";
        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("image_url", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();
        map.put("created_date",now);
        map.put("last_modified_date",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int productId = keyHolder.getKey().intValue();
        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "UPDATE product" + " SET " +
                "product_name = :productName," +
                "category =:category," +
                "image_url = :image_url," +
                "price = :price," +
                "stock = :stock," +
                "description = :description," +
                "last_modified_date = :last_modified_date" +
                " WHERE product_id = :productId;";
        Map<String, Object> map = new HashMap<>();
        map.put("productId",productId);
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("image_url", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();
        map.put("last_modified_date",now);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteProduct(Integer productId) {
        String sql = "Delete FROM product WHERE product_id = :productId;";
        Map<String, Object> map = new HashMap<>();
        map.put("productId",productId);
        namedParameterJdbcTemplate.update(sql, map);
    }
}
