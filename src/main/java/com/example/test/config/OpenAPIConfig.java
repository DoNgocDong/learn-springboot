package com.example.test.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                    name = "DoNgocDong",
                    email = "ngocdong2110.2003@gmail.com",
                    url = "https://github.com/DoNgocDong"
                ),
                title = "DEMO PROJECT OPEN API",
                description = "API Documentation for Demo project",
                version = "1.0"
        )
)
@Configuration
public class OpenAPIConfig {
}
