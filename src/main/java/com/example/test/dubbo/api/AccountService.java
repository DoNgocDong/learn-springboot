package com.example.test.dubbo.api;

import org.springframework.http.ResponseEntity;

public interface AccountService {

    ResponseEntity<String> hello(String name);

}
