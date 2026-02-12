package com.example.clothesshop.repository;

import com.example.clothesshop.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NewsRepository extends JpaRepository<News,Long> {
    List<News> getNewsByIsActive(boolean isActive);

    List<News> findAll();

    List<News> findAllByIsActive(boolean isActive);
}
