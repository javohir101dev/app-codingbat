package com.controller;

import com.entity.Language;
import com.model.payload.LanguageDto;
import com.model.response.Response;
import com.service.LanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/language")
public class LanguageController {

    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping
    public ResponseEntity<Response<Language>>  addLanguage(@RequestBody LanguageDto languageDto){
        return languageService.addLanguage(languageDto);
    }

    @GetMapping("/getLanguageById/{id}")
    public ResponseEntity<Response<Language>> getLanguageById(@PathVariable Long id){
        return languageService.getLanguageById(id);
    }

    @GetMapping
    public ResponseEntity<Response<List<Language>>> getAllLanguage(){
        return languageService.getAllLanguages();
    }

    @PutMapping("/editLanguageById/{id}")
    public ResponseEntity<Response<Language>> editLanguageById(@PathVariable Long id, @RequestBody LanguageDto languageDto){
        return languageService.editLanguageById(id, languageDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Language>> deleteLanguageById(@PathVariable Long id){
        return  languageService.deleteLanguageById(id);
    }

}
