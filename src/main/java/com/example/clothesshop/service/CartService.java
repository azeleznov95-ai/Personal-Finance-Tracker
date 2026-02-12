package com.example.clothesshop.service;

import com.example.clothesshop.dto.CartItemToChangeAmountRequestDto;
import com.example.clothesshop.dto.CartResponseDto;
import com.example.clothesshop.exeptions.BadCartRequestException;
import com.example.clothesshop.exeptions.CartNotFoundException;
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
    private Integer getTotalPrice(Cart cart){
        var totalPrice = 0;
        var items = cart.getCartItems();
        for(var item: items){
            totalPrice+= item.getPriceSnapshot()*item.getAmount();

        }
        return totalPrice;

    }
    public CartResponseDto getCart(String token){
        Long userId = jwToken.extractUserId(token);
        var cartOpt = cartRepository.findCartByUser_Id(userId);
        if (cartOpt.isEmpty()){
            var totalPrice = 0;
            Cart cart = new Cart();
            cartRepository.save(cart);
            return mapper.toResponse(cart,totalPrice);
        }
        else{
            Cart cart = cartOpt.get();
            var totalPrice = getTotalPrice(cart);

            return mapper.toResponse(cart,totalPrice);
        }

    }
    public CartResponseDto changeAmount(String token, CartItemToChangeAmountRequestDto requestDto){

        var userId = jwToken.extractUserId(token);
        var cartOpt = cartRepository.findCartByUser_Id(userId);


        if (cartOpt.isEmpty()){
            throw new CartNotFoundException("User's cart not found");
        }
        var cartItems = cartOpt.get().getCartItems();
        if (requestDto.getChangeAmount() ==0){
            cartItems.removeIf(item -> item.getId().equals(requestDto.getItemId()));


        }
        else {
            var requestItemId = requestDto.getItemId();
            var neededItem = cartItems
                    .stream()
                    .filter(item -> item.getId().equals(requestItemId))
                    .findFirst();
            if (neededItem.isEmpty()) {
                throw new BadCartRequestException("Needed item to change amount not in Cart");
            }
            var updatedItem = neededItem.get();
            updatedItem.setAmount(requestDto.getChangeAmount());
            cartItems.removeIf(item -> item.getId().equals(updatedItem.getId()));
            cartItems.add(updatedItem);
        }
        var updatedCart = cartOpt.get();
        updatedCart.setCartItems(cartItems);

        cartRepository.save(updatedCart);
        var totalPrice = getTotalPrice(updatedCart);
        return mapper.toResponse(updatedCart,totalPrice);


    }
}
