package com.example.clothesshop.controller;



import com.example.clothesshop.security.JWToken;
import com.example.clothesshop.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final JWToken jwToken;
    private final CartService cartService;
    CartController(CartService cartService,JWToken jwToken){
        this.cartService = cartService;
        this.jwToken = jwToken;
    }
    @GetMapping("/cart")
    public ResponseEntity<?> getCart(@RequestParam String token){
        var careResponse = cartService.getCart(token);
        return ResponseEntity.ok().build();
    }

}
