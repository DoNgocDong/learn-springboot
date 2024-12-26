package com.example.test.dubbo.provider;

import com.example.test.dubbo.api.AccountService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class AccountServiceImpl implements AccountService {
    @Override
    public String hello(String name) {
        return "hello " + name;
    }
}
