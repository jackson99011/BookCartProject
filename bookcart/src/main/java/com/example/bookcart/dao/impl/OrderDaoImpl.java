package com.example.bookcart.dao.impl;

import com.example.bookcart.dao.OrderDao;
import com.example.bookcart.dto.OrderQueryParams;
import com.example.bookcart.model.Order;
import com.example.bookcart.model.OrderItem;
import com.example.bookcart.rowmapper.OrderItemRowMapper;
import com.example.bookcart.rowmapper.OrderRowMapper;
import com.example.bookcart.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String addFilteringSql(String sql, Map<String, Object> map, OrderQueryParams orderQueryParams) {
        if (orderQueryParams.getUserId() != null) {
            sql = sql + " AND user_id = :userId";
            map.put("userId", orderQueryParams.getUserId());
        }
        return sql;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
        String sql = "SELECT oi.order_item_id, oi.order_id,oi.product_id,oi.quantity,oi.amount," +
                "p.product_name,p.image_url " +
                "FROM order_item as oi " +
                "LEFT JOIN product as p ON oi.product_id = p.product_id"+
                " WHERE oi.order_id = :orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        return namedParameterJdbcTemplate.query(sql, map, new OrderItemRowMapper());
    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "SELECT order_id, user_id,total_amount, created_date, last_modified_date " +
                "FROM `order` WHERE order_id = :orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());

        if (orderList.size() > 0)
            return orderList.get (0);
        else
            return null;
    }

    @Override
    public Integer createOrder(Integer userId,Integer totalAmount) {
        String sql = "INSERT INTO `order` (user_id, total_amount, created_date, last_modified_date)" +
                " VALUES (:userId, :totalAmount, :created_date,:last_modified_date)";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);

        Date now = new Date();
        map.put("created_date",now);
        map.put("last_modified_date",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int orderId = keyHolder.getKey().intValue();

        return orderId;
    }

    @Override
    public void createOrderItem(Integer orderId, List<OrderItem> orderItemList) {

        String sql = "INSERT INTO order_item(order_id,product_id,quantity,amount)" +
                " VALUES (:orderId, :productId, :quantity, :amount)";
        //batchUpdate
        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderItemList.size()];

        for (int i = 0; i < orderItemList.size(); i++)
        {
            OrderItem orderItem = orderItemList.get(i);
            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("orderId",orderId);
            parameterSources[i].addValue("productId",orderItem.getProductId());
            parameterSources[i].addValue("quantity",orderItem.getQuantity());
            parameterSources[i].addValue("amount",orderItem.getAmount());
        }

        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
    }

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        String sql = "SELECT count(*) FROM `order` where 1=1";
        Map<String,Object> map = new HashMap<>();
        sql = addFilteringSql(sql,map,orderQueryParams);
        return namedParameterJdbcTemplate.queryForObject(sql, map,Integer.class);
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        String sql = "SELECT order_id, user_id, total_amount, created_date, last_modified_date FROM `order` WHERE 1=1";
        Map<String,Object> map = new HashMap<>();
        //查詢條件
        sql = addFilteringSql(sql,map,orderQueryParams);
        //排序
        sql += " ORDER BY created_date DESC";
        //分頁條件
        sql += " LIMIT :limit OFFSET :offset";
        map.put("limit", orderQueryParams.getLimit());
        map.put("offset", orderQueryParams.getOffset());

        return namedParameterJdbcTemplate.query(sql, map,new OrderRowMapper());
    }

}
