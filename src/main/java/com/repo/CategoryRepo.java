package com.repo;

import com.entity.Category;
import com.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long>{



}