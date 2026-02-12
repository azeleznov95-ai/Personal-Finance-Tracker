package com.example.clothesshop.dto;

import com.example.clothesshop.enums.CartStatus;
import com.example.clothesshop.model.CartItem;
import com.example.clothesshop.model.Users;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartResponseDto {
        private Long id;
        private Users user;
        private List<CartItem> cartItems;
        private CartStatus cartStatus = CartStatus.EMPTY;
        private Integer totalPrice;





}
