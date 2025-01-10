package com.example.test.utils;

import com.example.test.dtos.response.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {
    public <T> ResponseEntity<ApiResponseDTO<T>> buildApiResponse(HttpStatus status, String message, T data) {
        ApiResponseDTO<T> res = ApiResponseDTO.<T>builder()
                .code(status.value())
                .data(data)
                .message(message)
                .build();

        return ResponseEntity.status(status).body(res);
    }
}
