package com.example.test.dubbo.provider;

import com.example.test.dubbo.api.AccountProvider;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.http.ResponseEntity;

@DubboService
public class AccountServiceImpl implements AccountProvider {

    @Override
    public ResponseEntity<String> hello(String name) {
        String res = "Hello " + name + "!";
        return ResponseEntity.ok(res);
    }
}
