package com.example.personalfinancetracker.repository;

import com.example.personalfinancetracker.model.Category;
import com.example.personalfinancetracker.model.FinanceTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FinanceTransactionRepository extends JpaRepository<FinanceTransaction,Long> {
    Optional<FinanceTransaction> findAllByCategory(Category category);
}
