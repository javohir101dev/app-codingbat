package com.service;

import com.entity.Language;
import com.model.payload.LanguageDto;
import com.model.response.Response;
import com.repo.LanguageRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    private final LanguageRepo languageRepo;

    public LanguageService(LanguageRepo languageRepo) {
        this.languageRepo = languageRepo;
    }

    public ResponseEntity<Response<Language>> addLanguage(LanguageDto languageDto){

        if (languageRepo.existsByName(languageDto.getName())){
            return new ResponseEntity<>(new Response<>("This language is already exists"), HttpStatus.CONFLICT);
        }

        Language language = new Language();
        language.setName(languageDto.getName());
        Language savedLanguage = languageRepo.save(language);
        return ResponseEntity.ok(new Response<>(savedLanguage));
    }

    public ResponseEntity<Response<Language>> getLanguageById(Long id){
        Optional<Language> optionalLanguage = languageRepo.findById(id);
        if (!optionalLanguage.isPresent()){
            return new ResponseEntity<>(new Response<>("Language is not found"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new Response<>(optionalLanguage.get()));
    }

    public ResponseEntity<Response<List<Language>>> getAllLanguages(){
        return ResponseEntity.ok(new Response<>(languageRepo.findAll()));
    }

    public ResponseEntity<Response<Language>> editLanguageById(Long id, LanguageDto languageDto){
        Optional<Language> optionalLanguage = languageRepo.findById(id);
        if (!optionalLanguage.isPresent()){
            return new ResponseEntity<>(new Response<>("Language is not found"), HttpStatus.NOT_FOUND);
        }

        Language language = optionalLanguage.get();
        language.setName(languageDto.getName());
        Language savedLanguage = languageRepo.save(language);
        return ResponseEntity.ok(new Response<>(savedLanguage));
    }


    public ResponseEntity<Response<Language>> deleteLanguageById(Long id){
        Optional<Language> optionalLanguage = languageRepo.findById(id);
        if (!optionalLanguage.isPresent()){
            return new ResponseEntity<>(new Response<>("Language is not found"), HttpStatus.NOT_FOUND);
        }

        Language language = optionalLanguage.get();
        languageRepo.deleteById(id);
        return ResponseEntity.ok(new Response<>(language));
    }



}
