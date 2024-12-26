package com.example.test.controller;

import com.example.test.dubbo.api.AccountProvider;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DubboClient {

    @DubboReference
    private AccountProvider accountProvider;

    @GetMapping("/dubbo")
    public ResponseEntity<String> hello(
        @RequestParam(name = "name", defaultValue = "world") String name
    ) {
        return accountProvider.hello(name);
    }
}