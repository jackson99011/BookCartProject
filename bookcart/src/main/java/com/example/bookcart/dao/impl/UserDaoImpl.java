package com.example.bookcart.dao.impl;


import com.example.bookcart.dao.UserDao;
import com.example.bookcart.dto.UserRegisterRequest;
import com.example.bookcart.model.Product;
import com.example.bookcart.model.User;
import com.example.bookcart.rowmapper.ProductRowMapper;
import com.example.bookcart.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer countUser(User user) {
        return null;
    }

    @Override
    public List<User> getUsers(User user) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user where email = :email";
        Map<String,Object> map = new HashMap<>();
        map.put("email",email);
        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        if (userList.size() > 0)
            return  userList.get(0);
        else
            return null;
    }

    @Override
    public User getUserById(Integer userId) {
        String sql = "SELECT * FROM user where user_id = :userId";
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        if (userList.size() > 0)
            return  userList.get(0);
        else
            return null;
    }

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        String sql = "INSERT INTO user(email,password,created_date,last_modified_date)" +
                "VALUES(:email,:password,:created_date,:last_modified_date)";

        Map<String, Object> map = new HashMap<>();
        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());

        Date now = new Date();
        map.put("created_date",now);
        map.put("last_modified_date",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void updateUser(Integer UserId, User user) {

    }

    @Override
    public void deleteUser(Integer UserId) {

    }
}
