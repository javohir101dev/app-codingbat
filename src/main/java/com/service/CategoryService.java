package com.service;

import com.entity.Category;
import com.entity.Language;
import com.model.payload.CategoryDto;
import com.model.response.Response;
import com.repo.CategoryRepo;
import com.repo.LanguageRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private final LanguageRepo languageRepo;

    public CategoryService(CategoryRepo categoryRepo, LanguageRepo languageRepo) {
        this.categoryRepo = categoryRepo;
        this.languageRepo = languageRepo;
    }

    public ResponseEntity<Response<Category>> addCategory(CategoryDto categoryDto){
        List<Long> languageIds = categoryDto.getLanguageIds();
        List<Language> languages = new ArrayList<>();

        for (Long languageId : languageIds) {
            Optional<Language> optionalLanguage = languageRepo.findById(languageId);
            if (!optionalLanguage.isPresent()){
                return new ResponseEntity<>(new Response<>("Language with id " + languageId + " is not found"), HttpStatus.NOT_FOUND);
            }
            languages.add(optionalLanguage.get());
        }

        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setLanguage(languages);
        Category savedCategory = categoryRepo.save(category);
        return ResponseEntity.ok(new Response<>(savedCategory));
    }

    public ResponseEntity<Response<Category>> getCategoryById(Long id){
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        if (!optionalCategory.isPresent()){
            return new ResponseEntity<>(new Response<>("Category is not found"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new Response<>(optionalCategory.get()));
    }

    public ResponseEntity<Response<List<Category>>> getAllCategories(){
        return ResponseEntity.ok(new Response<>(categoryRepo.findAll()));
    }

    public ResponseEntity<Response<Category>> editCategoryById(Long id, CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        if (!optionalCategory.isPresent()){
            return new ResponseEntity<>(new Response<>("Category is not found"), HttpStatus.NOT_FOUND);
        }

        List<Long> languageIds = categoryDto.getLanguageIds();
        List<Language> languages = new ArrayList<>();

        for (Long languageId : languageIds) {
            Optional<Language> optionalLanguage = languageRepo.findById(languageId);
            if (!optionalLanguage.isPresent()){
                return new ResponseEntity<>(new Response<>("Language with id " + languageId + " is not found"), HttpStatus.NOT_FOUND);
            }
            languages.add(optionalLanguage.get());
        }

        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setLanguage(languages);
        Category savedCategory = categoryRepo.save(category);
        return ResponseEntity.ok(new Response<>(savedCategory));
    }


    public ResponseEntity<Response<Category>> deleteCategoryById(Long id){
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        if (!optionalCategory.isPresent()){
            return new ResponseEntity<>(new Response<>("Category is not found"), HttpStatus.NOT_FOUND);
        }

        Category category = optionalCategory.get();
        categoryRepo.deleteById(id);
        return ResponseEntity.ok(new Response<>(category));
    }



}
