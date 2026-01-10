package com.example.personalfinancetracker.repository;

import com.example.personalfinancetracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findAllByCategory(Category category) ;
}
