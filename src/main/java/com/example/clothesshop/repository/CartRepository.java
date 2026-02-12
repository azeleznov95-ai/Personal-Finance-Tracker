package com.example.clothesshop.repository;

import com.example.clothesshop.model.Cart;

import com.example.clothesshop.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findCartByUser_Id(Long id);

    Long user(Users user);
}
