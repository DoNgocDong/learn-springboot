package com.example.test.dtos.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseDTO {
    private int code;

    @NotBlank(message = "err msg is required")
    private String message;
}
