package com.example.test.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseDTO {
    @Schema(description = "Error code", example = "400")
    private int code;

    @NotBlank(message = "err msg is required")
    @Schema(description = "Message accompanying", example = "Invalid account info")
    private String message;

    @Schema(description = "List errors description")
    private List<String> errors;
}
