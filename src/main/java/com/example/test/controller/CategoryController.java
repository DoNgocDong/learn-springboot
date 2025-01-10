package com.example.test.controller;

import com.example.test.dtos.payload.CategoryDTO;
import com.example.test.dtos.response.ApiResponseDTO;
import com.example.test.handler.CategoryHandler;
import com.example.test.messaging.CategoryMsgEmitter;
import com.example.test.model.Category;
import com.example.test.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Tag(name = "CategoryController")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryHandler categoryHandler;
    private final ResponseUtil responseUtil;

    @GetMapping({"/", ""})
    public ResponseEntity<ApiResponseDTO<List<Category>>> getCategories() {
        List<Category> data = categoryHandler.findAll();

        HttpStatus status = HttpStatus.OK;
        String msg = "Get categories successfully";

        return responseUtil.buildApiResponse(status, msg, data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Category>> getCategoryById(@PathVariable(name = "id") Long id) {
        Category category = categoryHandler.findById(id);

        HttpStatus status = HttpStatus.OK;
        String msg = "Get category successfully";
//        categoryMsgEmitter.send(category);

        return responseUtil.buildApiResponse(status, msg, category);
    }

    @PostMapping({"/", ""})
    public ResponseEntity<ApiResponseDTO<Category>> createCategory(@Valid @RequestBody CategoryDTO data) {
        Category category = categoryHandler.createCategory(data);

        HttpStatus status = HttpStatus.CREATED;
        String msg = "Created category!";

        return responseUtil.buildApiResponse(status, msg, category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Category>> updateCategory(@PathVariable(name = "id") Long id,
                                                                   @Valid @RequestBody CategoryDTO data)
    {
        Category category = categoryHandler.updateCategory(id, data);

        HttpStatus status = HttpStatus.OK;
        String msg = "Updated category!";

        return responseUtil.buildApiResponse(status, msg, category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Category>> deleteCategoryById(@PathVariable(name = "id") Long id) {
        Category category = categoryHandler.deleteById(id);

        HttpStatus status = HttpStatus.OK;
        String msg = "Deleted category!";

        return responseUtil.buildApiResponse(status, msg, category);
    }
}
