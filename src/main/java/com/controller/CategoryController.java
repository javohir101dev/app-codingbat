package com.controller;

import com.entity.Category;
import com.model.payload.CategoryDto;
import com.model.response.Response;
import com.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Response<Category>>  addCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.addCategory(categoryDto);
    }

    @GetMapping("/getCategoryById/{id}")
    public ResponseEntity<Response<Category>> getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping
    public ResponseEntity<Response<List<Category>>> getAllCategory(){
        return categoryService.getAllCategories();
    }

    @PutMapping("/editCategoryById/{id}")
    public ResponseEntity<Response<Category>> editCategoryById(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        return categoryService.editCategoryById(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Category>> deleteCategoryById(@PathVariable Long id){
        return  categoryService.deleteCategoryById(id);
    }

}
