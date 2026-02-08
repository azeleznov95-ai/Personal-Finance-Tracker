package com.example.clothesshop.repository;

import com.example.clothesshop.model.Order;
import com.example.clothesshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findOrderByUser(User user);
    
}
