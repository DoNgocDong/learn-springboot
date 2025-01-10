package com.example.test.dtos.payload;

import com.example.test.model.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    @NotBlank(message = "product_name is required")
    private String name;

    private String description;

    private Long category_id;

    @Min(value = 0, message = "price is required")
    private int price;
}
