package com.example.clothesshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {
  private int amount;
  private String description;
  private Long categoryId;
}
