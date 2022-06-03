package com.example.bookcart.service.impl;

import com.example.bookcart.dao.ProductDao;
import com.example.bookcart.dao.UserDao;
import com.example.bookcart.dto.ProductQueryParams;
import com.example.bookcart.dto.ProductRequest;
import com.example.bookcart.dto.UserRegisterRequest;
import com.example.bookcart.model.Product;
import com.example.bookcart.model.User;
import com.example.bookcart.service.ProductService;
import com.example.bookcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
