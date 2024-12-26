package com.example.test.dubbo.api;

import org.springframework.http.ResponseEntity;

public interface AccountProvider {

    ResponseEntity<String> hello(String name);

}
