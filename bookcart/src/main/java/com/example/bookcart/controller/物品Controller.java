package com.example.bookcart.controller;

import com.example.bookcart.model.物品;
import com.example.bookcart.service.物品Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
public class 物品Controller {

    @Autowired
    private 物品Service 物品庫Service;

    @GetMapping("/物品")
    public ResponseEntity<物品> 由物品編號取得物品() {
        物品 取得物品 = 物品庫Service.由物品編號取得物品(1);
        return ResponseEntity.status(HttpStatus.OK).body(取得物品);
    }
}
