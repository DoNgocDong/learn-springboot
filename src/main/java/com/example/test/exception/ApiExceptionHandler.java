package com.example.test.exception;

import com.example.test.dtos.response.ErrorResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;

@RestControllerAdvice
public class ApiExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        Locale locale = request.getLocale();
        String message = messageSource.getMessage("err.notfound", null, locale);

        ErrorResponseDTO res = ErrorResponseDTO
                .builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(message)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }
}
