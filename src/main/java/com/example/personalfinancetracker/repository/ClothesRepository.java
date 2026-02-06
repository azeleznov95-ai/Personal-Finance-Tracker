package com.example.personalfinancetracker.repository;

import com.example.personalfinancetracker.model.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothesRepository extends JpaRepository<Clothes,Long> {
    List<Clothes> findClothesByName(String name);
}
