package com.example.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/")
@Tag(name = "HelloController")
public class HelloController {
    private final MessageSource messageSource;

    public HelloController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("<h1>HOME PAGE</h1>");
    }

    @Operation(summary = "Greeting API", description = "Greeting your name")
    @ApiResponse(responseCode = "200", description = "OK",
        content = {
            @Content(examples = @ExampleObject(
                    value = "Hello World"
            ))
    })
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
