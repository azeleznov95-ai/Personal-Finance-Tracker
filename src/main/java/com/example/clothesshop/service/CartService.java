package com.example.clothesshop.service;

import com.example.clothesshop.dto.CartResponseDto;
import com.example.clothesshop.mapper.CartMapper;
import com.example.clothesshop.model.Cart;
import com.example.clothesshop.repository.CartRepository;
import com.example.clothesshop.security.JWToken;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final JWToken jwToken;
    private final CartMapper mapper;
    CartService(CartRepository cartRepository, JWToken jwToken, CartMapper mapper){
        this.cartRepository = cartRepository;
        this.jwToken = jwToken;
        this.mapper = mapper;
    }
    public CartResponseDto getCart(String token){
        Long userId = jwToken.extractUserId(token);
        var cartOpt = cartRepository.findCartByUser_Id(userId);
        if (cartOpt.isEmpty()){
            Cart cart = new Cart();
            cartRepository.save(cart);

            return mapper.toResponse(cart);
        }
        else{
            Cart cart = cartOpt.get();
            return mapper.toResponse(cart);
        }

    }
}
