package com.example.test.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseDTO<T> {
    @Schema(description = "Status code", example = "200")
    private int code;

    @Schema(description = "Message accompanying", example = "Get data success!")
    private String message;

    @Schema(description = "Response Payload")
    private T data;
}
