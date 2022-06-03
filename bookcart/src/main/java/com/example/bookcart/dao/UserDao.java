package com.example.bookcart.dao;

import com.example.bookcart.dto.ProductRequest;
import com.example.bookcart.dto.UserRegisterRequest;
import com.example.bookcart.model.User;

import java.util.List;

public interface UserDao {
    Integer countUser(User user);
    List<User> getUsers(User user);
    User getUserById(Integer userId);
    Integer createUser(UserRegisterRequest userRegisterRequest);
    void updateUser(Integer UserId,User user);
    void deleteUser(Integer UserId);
}
