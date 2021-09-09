package com.repo;

import com.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepo extends JpaRepository<Language, Long>{

    boolean existsByName(String name);


}