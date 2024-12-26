package com.example.test.controller;

import com.example.test.dubbo.api.AccountService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DubboClient {

    @DubboReference
    private AccountService accountService;

    @GetMapping("/dubbo")
    public ResponseEntity<String> hello(
        @RequestParam(name = "name", defaultValue = "world") String name
    ) {
        return accountService.hello(name);
    }
}
