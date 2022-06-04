package com.example.bookcart.service.impl;

import com.example.bookcart.dao.UserDao;
import com.example.bookcart.dto.UserLoginRequest;
import com.example.bookcart.dto.UserRegisterRequest;
import com.example.bookcart.model.User;
import com.example.bookcart.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class UserServiceImpl implements UserService {
    private final static Logger log =  LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        if (user != null)
        {
            log.warn("該 email {} 已經被註冊",userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //使用MD5 生成密碼雜湊值
        //String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        //使用SHA-256 生成密碼湊
        String sha256 = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(userRegisterRequest.getPassword().getBytes(StandardCharsets.UTF_8));
            sha256 = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        userRegisterRequest.setPassword(sha256);

        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());
        //比對使用者是否存在
        if (user == null)
        {
            log.warn("該 email {} 尚未註冊",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //比對MD5密碼值是否正確
        //String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());
        String sha256 = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(userLoginRequest.getPassword().getBytes(StandardCharsets.UTF_8));
            //將String轉換為十六進位制
            sha256 = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        if (user.getPassword().equals(sha256))
        {
            return user;
        }
        else
        {
            log.warn("該 email {} 密碼不正確",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
