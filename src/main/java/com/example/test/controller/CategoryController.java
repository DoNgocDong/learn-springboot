package com.example.test.controller;

import com.example.test.dtos.response.ApiResponseDTO;
import com.example.test.handler.CategoryHandler;
import com.example.test.model.Category;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Tag(name = "CategoryController")
public class CategoryController {
    private final CategoryHandler categoryHandler;

    public CategoryController(CategoryHandler categoryHandler) {
        this.categoryHandler = categoryHandler;
    }

    @GetMapping("")
    public ResponseEntity<ApiResponseDTO<List<Category>>> getCategories() {
        List<Category> data = categoryHandler.findAll();

        HttpStatus status = HttpStatus.OK;
        String msg = "Get categories successfully";

        return buildApiResponse(status, msg, data);
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

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Category>> updateCategory(@PathVariable(name = "id") Long id,
                                                                   @Valid @RequestBody Category data)
    {
        Category category = categoryHandler.updateCategory(id, data);

        HttpStatus status = HttpStatus.OK;
        String msg = "Updated category!";

        return buildApiResponse(status, msg, category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Category>> deleteCategoryById(@PathVariable(name = "id") Long id) {
        Category category = categoryHandler.deleteById(id);

        HttpStatus status = HttpStatus.OK;
        String msg = "Deleted category!";

        return buildApiResponse(status, msg, category);
    }

    private <T> ResponseEntity<ApiResponseDTO<T>> buildApiResponse(HttpStatus status, String message, T data) {
        ApiResponseDTO<T> res = ApiResponseDTO.<T>builder()
                .code(status.value())
                .data(data)
                .message(message)
                .build();

        return ResponseEntity.status(status).body(res);
    }
}
