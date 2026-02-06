package com.example.personalfinancetracker.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionResponse {
    private int amount;
    private String description;
    private String categoryName;
    private LocalDateTime createdAt;
}

