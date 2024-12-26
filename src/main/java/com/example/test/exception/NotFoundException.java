package com.example.test.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundException extends ResourceNotFoundException {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }
}
