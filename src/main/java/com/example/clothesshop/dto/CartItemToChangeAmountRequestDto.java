package com.example.clothesshop.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CartItemToChangeAmountRequestDto {
    @NonNull
    private Integer changeAmount;
    @NonNull
    private Long itemId;

}
