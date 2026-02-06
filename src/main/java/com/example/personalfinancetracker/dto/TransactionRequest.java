package com.example.personalfinancetracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {
  private int amount;
  private String description;
  private Long categoryId;
}
