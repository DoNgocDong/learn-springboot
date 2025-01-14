package com.example.test.exception;

import com.example.test.dtos.response.ErrorResponseDTO;
import com.example.test.utils.LocaleUtils;
import com.example.test.utils.MessageKeys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {
    private final LocaleUtils localeUtils;

    public ApiExceptionHandler(LocaleUtils localeUtils) {
        this.localeUtils = localeUtils;
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNoResourceFoundException(NoResourceFoundException e, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = localeUtils.getLocaleMsg(MessageKeys.PATH_NOT_FOUND, request);

        return buildResponseError(status, message, null, request, e);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = localeUtils.getLocaleMsg(MessageKeys.RESOURCE_NOT_FOUND_ERR, request);

        return buildResponseError(status, message, null, request, e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = localeUtils.getLocaleMsg(MessageKeys.REQUEST_BODY_INVALID, request);

        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        return buildResponseError(status, message, errors, request, e);
    }

    @ExceptionHandler(ResourceExistedException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceExistedException(ResourceExistedException e, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        String msg = localeUtils.getLocaleMsg(e.getMsgKey(), request, e.getArgs());

        return buildResponseError(status, msg, null, request, e);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = localeUtils.getLocaleMsg(MessageKeys.INTERNAL_SERVER_ERR, request);

        return buildResponseError(status, message, null, request, e);
    }

    private ResponseEntity<ErrorResponseDTO> buildResponseError(HttpStatus status, String message, List<String> errors, WebRequest request, Exception e) {
        ErrorResponseDTO res = ErrorResponseDTO
                .builder()
                .code(status.value())
                .message(message)
                .errors(errors)
                .build();

        if(HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            log.error(e.getMessage(), e);
        }

        return ResponseEntity.status(status).body(res);
    }
}
