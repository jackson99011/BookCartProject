package com.example.bookcart.service;

import com.example.bookcart.dto.ProductQueryParams;
import com.example.bookcart.dto.ProductRequest;
import com.example.bookcart.dto.UserLoginRequest;
import com.example.bookcart.dto.UserRegisterRequest;
import com.example.bookcart.model.Product;
import com.example.bookcart.model.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    Integer register(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
    User login(UserLoginRequest userLoginRequest);
}
