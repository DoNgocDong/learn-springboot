package com.example.test.dubbo.provider;

import com.example.test.dubbo.api.AccountService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.http.ResponseEntity;

@DubboService
public class AccountServiceImpl implements AccountService {

    @Override
    public ResponseEntity<String> hello(String name) {
        String res = "Hello " + name + "!";
        return ResponseEntity.ok(res);
    }
}
