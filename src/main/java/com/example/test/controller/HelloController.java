package com.example.test.controller;

import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;

@RestController
@RequestMapping(value = "/", consumes = MediaType.ALL_VALUE)
public class HelloController {
    private final MessageSource messageSource;

    public HelloController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/greeting")
    public ResponseEntity<String> sayHello(
        @RequestParam(name = "name", defaultValue = "World") String name,
        WebRequest request
    ){
        Locale locale = request.getLocale();
        final String res =  messageSource.getMessage("greeting", new Object[]{name}, locale);

        return ResponseEntity.ok(res);
    }
}
