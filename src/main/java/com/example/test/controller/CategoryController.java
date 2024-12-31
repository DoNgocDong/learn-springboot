package com.example.test.controller;

import com.example.test.dtos.response.ApiResponseDTO;
import com.example.test.handler.CategoryHandler;
import com.example.test.model.Category;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/categories")
@Tag(name = "CategoryController")
public class CategoryController {
    private final CategoryHandler categoryHandler;

    public CategoryController(CategoryHandler categoryHandler) {
        this.categoryHandler = categoryHandler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Category>> getCategoryById(@PathVariable(name = "id") Long id) {
        Category category = categoryHandler.findById(id);

        HttpStatus status = HttpStatus.OK;
        String msg = "Get category successfully";

        return buildApiResponse(status, msg, category);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponseDTO<Category>> createCategory(@Valid @RequestBody Category data) {
        Category category = categoryHandler.createCategory(data);

        HttpStatus status = HttpStatus.CREATED;
        String msg = "Created category!";

        return buildApiResponse(status, msg, category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Category>> deleteCategoryById(@PathVariable(name = "id") Long id) {
        Category category = categoryHandler.deleteById(id);

        HttpStatus status = HttpStatus.OK;
        String msg = "Deleted category!";

        return buildApiResponse(status, msg, category);
    }

    private ResponseEntity<ApiResponseDTO<Category>> buildApiResponse(HttpStatus status, String message, Category data) {
        ApiResponseDTO<Category> res = ApiResponseDTO.<Category>builder()
                .code(status.value())
                .data(data)
                .message(message)
                .build();

        return ResponseEntity.status(status).body(res);
    }
}
