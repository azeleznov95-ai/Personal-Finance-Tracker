package com.example.clothesshop.service;

import com.example.clothesshop.dto.CartItemToChangeAmountRequestDto;
import com.example.clothesshop.dto.CartResponseDto;
import com.example.clothesshop.enums.CartStatus;
import com.example.clothesshop.exeptions.BadCartRequestException;
import com.example.clothesshop.exeptions.CartNotFoundException;
import com.example.clothesshop.mapper.CartMapper;

import com.example.clothesshop.model.Cart;
import com.example.clothesshop.model.CartItem;
import com.example.clothesshop.repository.CartRepository;
import com.example.clothesshop.repository.ClothesRepository;
import com.example.clothesshop.repository.UserRepository;
import com.example.clothesshop.security.JWToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;



@Service
public class CartService {
    private final CartRepository cartRepository;
    private final JWToken jwToken;
    private final CartMapper cartMapper;
    private final ClothesRepository clothesRepository;
    private final UserRepository userRepository;

    CartService(CartRepository cartRepository,
                JWToken jwToken,
                CartMapper cartMapper,
                ClothesRepository clothesRepository,
                UserRepository usersRepository
                ){
        this.cartRepository = cartRepository;
        this.jwToken = jwToken;
        this.cartMapper = cartMapper;
        this.clothesRepository = clothesRepository;
        this.userRepository = usersRepository;
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
            cart.setUser((userRepository.findUserById(userId)));
            cart.setCartItems(List.of());
            cartRepository.save(cart);
            return cartMapper.toResponse(cart,totalPrice);
        }
        else{
            Cart cart = cartOpt.get();
            var totalPrice = getTotalPrice(cart);

            return cartMapper.toResponse(cart,totalPrice);
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
        return cartMapper.toResponse(updatedCart,totalPrice);


    }
    public CartResponseDto addToCart(String token,Long itemId){
        var cart = getCart(token);
        var clothOpt = clothesRepository.findClothesById(itemId);
        if (clothOpt.isEmpty()){
            throw new BadCartRequestException("Item with this id not found");
        }
        var cloth= clothOpt.get();

        var cartItem = new CartItem();
        cartItem.setCloth(cloth);
        cartItem.setAmount(1);
        cartItem.setAddedAt(LocalDateTime.now());
        cartItem.setImageUrl(cloth.getImageUrl());
        cartItem.setPriceSnapshot(cloth.getPrice());
        cart.setCartStatus(CartStatus.UPDATED);
        var cartItems =cart.getCartItems();
        cartItems.add(cartItem);
        return cart;
    }
    public CartResponseDto removeFromCart(String token,Long itemId){
            var cartOpt = cartRepository.findCartByUser_Id(jwToken.extractUserId(token));
            if (cartOpt.isEmpty()){
                throw new CartNotFoundException("Cart is not found");
            }
            var cart = cartOpt.get();
            var cartItems = cart.getCartItems();
            if (cartItems.isEmpty()){
                throw  new BadCartRequestException("Nothing to remove");
            }

            cartItems.removeIf(item -> item.getId().equals(itemId));
            cart.setCartItems(cartItems);
            var totalPrice = getTotalPrice(cart);
            return cartMapper.toResponse(cart,totalPrice);
}}
