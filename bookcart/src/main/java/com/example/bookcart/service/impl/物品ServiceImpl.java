package com.example.bookcart.service.impl;


import com.example.bookcart.dao.物品Dao;
import com.example.bookcart.model.物品;
import com.example.bookcart.service.物品Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class 物品ServiceImpl implements 物品Service {

    @Autowired
    private 物品Dao 新物品Dao;


    @Override
    public 物品 由物品編號取得物品(Integer 物品編號) {
        return 新物品Dao.由物品編號取得物品(物品編號);
    }
}
