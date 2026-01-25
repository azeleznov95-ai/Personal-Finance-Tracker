package com.example.personalfinancetracker.repository;

import com.example.personalfinancetracker.model.Category;
import com.example.personalfinancetracker.model.FinanceTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FinanceTransactionRepository extends JpaRepository<FinanceTransaction,Long> {
    List<FinanceTransaction> findAllByUserId(Long id);
}
