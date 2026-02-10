package com.example.clothesshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClothChangeAmountRequestDto {
    Long Id;
    Integer delta;
}
