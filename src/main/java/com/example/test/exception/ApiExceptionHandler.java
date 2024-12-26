package com.example.test.exception;

import com.example.test.dtos.response.ErrorResponseDTO;
import com.example.test.utils.LocaleUtils;
import com.example.test.utils.MessageKeys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {
    private final LocaleUtils localeUtils;

    public ApiExceptionHandler(LocaleUtils localeUtils) {
        this.localeUtils = localeUtils;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = localeUtils.getLocaleMsg(MessageKeys.NOT_FOUND_ERR, request);

        ErrorResponseDTO res = ErrorResponseDTO
                .builder()
                .code(status.value())
                .message(message)
                .build();

        log.error(e.getMessage(), e);
        return ResponseEntity.status(status).body(res);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = localeUtils.getLocaleMsg(MessageKeys.INTERNAL_SERVER_ERR, request);

        ErrorResponseDTO res = ErrorResponseDTO
                .builder()
                .code(status.value())
                .message(message)
                .build();

        return ResponseEntity.status(status).body(res);
    }
}
