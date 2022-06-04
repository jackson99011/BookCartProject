package com.example.bookcart.dao.impl;


import com.example.bookcart.dao.物品Dao;
import com.example.bookcart.model.物品;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class 物品DaoImpl implements 物品Dao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public 物品 由物品編號取得物品(Integer 物品編號) {
        物品 新物品 = new 物品();
        新物品.set物品編號(1);
        新物品.set名稱("桌子");
        新物品.set價格(300);
        return 新物品;
    }
}
